package com.sagar.watchnext.activities.search.tv;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.sagar.watchnext.adapters.search.SearchCard;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.repo.TmdbTvRepo;
import com.sagar.watchnext.utils.GenreUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 14:16
 */
@TvSearchFragmentScope
public class Presenter implements Contract.Presenter {
    private Contract.View view;
    private TmdbTvRepo tvRepo;

    private CompositeDisposable disposable;

    private List<Show> searchResult;
    private String forQuery;

    private Subject<String> throttle;


    @Inject
    public Presenter(Contract.View view, TmdbTvRepo tvRepo) {
        this.view = view;
        this.tvRepo = tvRepo;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        disposable = new CompositeDisposable();
        throttle = PublishSubject.create();
        disposable.add(throttle.filter(q -> q.length() > 0)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(q -> {
                    if (searchResult != null) {
                        searchResult.clear();
                        view.notifyAdapter();
                    }
                    view.showProgress();
                    view.resetEndlessLoader();
                    disposable.add(
                            tvRepo.searchByQuery(q).
                                    subscribeOn(Schedulers.io()).
                                    observeOn(AndroidSchedulers.mainThread()).
                                    subscribe(shows -> {
                                                if (shows.getShows().size() < 1) {
                                                    view.showNoMatchMessage();
                                                } else {
                                                    view.hideErrorMessage();
                                                }
                                                view.hideProgress();
                                                searchResult = shows.getShows();
                                                view.notifyAdapter();
                                            },
                                            error -> view.onErrorLoadingShowList())
                    );

                })
        );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }

    @Override
    public void query(String query) {
        forQuery = query;
        throttle.onNext(query);
    }

    @Override
    public int getCardsCount() {
        return (searchResult != null) ? searchResult.size() : 0;

    }

    @Override
    public void onBindCard(SearchCard card, int position) {

        card.setImage(searchResult.get(position).getPosterPath());
        card.setTitle(searchResult.get(position).getName());

        String firstAirDate = searchResult.get(position).getFirstAirDate();
        if (!firstAirDate.isEmpty()) {
            card.setYear(firstAirDate.substring(0, 4));
        }

        StringBuilder genreString = new StringBuilder();
        List<Integer> genres = searchResult.get(position).getGenreIds();
        int index = 1;
        int size = genres.size();
        for (Integer genre : genres) {
            if (index++ < size) {
                genreString.append(GenreUtil.getTvGenreById(genre)).append(", ");
            } else {
                genreString.append(GenreUtil.getTvGenreById(genre));
            }
        }
        card.setGenre(genreString.toString());

    }

    @Override
    public void onRecyclerItemClick(int position) {
        view.startTvDetailActivity(searchResult.get(position).getId());
    }

    @Override
    public void onLoadMore(int pageToLoad) {
        String cQuery = forQuery;
        view.showProgress();
        disposable.add(tvRepo.searchByQuery(cQuery, pageToLoad).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                            if (!forQuery.equalsIgnoreCase(cQuery)) {
                                return;
                            }
                            searchResult.addAll(shows.getShows());
                            view.notifyAdapter();
                            view.hideProgress();
                        }
                )
        );
    }
}