package com.sagar.watchnext.activities.search.movies;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.sagar.watchnext.adapters.search.SearchCard;
import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
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
 * Created by SAGAR MAHOBIA on 14-Jul-18. at 00:24
 */

@MovieSearchFragmentScope
public class Presenter implements Contract.Presenter {
    private Contract.View view;

    private TmdbMovieRepo movieRepo;

    private CompositeDisposable disposable;

    private List<Movie> searchResult;
    private String forQuery;

    private Subject<String> throttle;


    @Inject
    public Presenter(Contract.View view, TmdbMovieRepo movieRepo) {
        this.view = view;
        this.movieRepo = movieRepo;
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
                            movieRepo.searchByQuery(q).
                                    subscribeOn(Schedulers.io()).
                                    observeOn(AndroidSchedulers.mainThread()).
                                    subscribe(movieList -> {
                                                if (movieList.getMovies().size() < 1) {
                                                    view.showNoMatchMessage();
                                                } else {
                                                    view.hideErrorMessage();
                                                }
                                                searchResult = movieList.getMovies();
                                                view.hideProgress();
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
        card.setTitle(searchResult.get(position).getTitle());

        String releaseDate = searchResult.get(position).getReleaseDate();
        if (!releaseDate.isEmpty()) {
            card.setYear(releaseDate.substring(0, 4));
        }

        StringBuilder genreString = new StringBuilder();
        List<Integer> genres = searchResult.get(position).getGenreIds();
        int index = 1;
        int size = genres.size();
        for (Integer genre : genres) {
            if (index++ < size) {
                genreString.append(GenreUtil.getMovieGenreById(genre)).append(", ");
            } else {
                genreString.append(GenreUtil.getMovieGenreById(genre));
            }
        }
        card.setGenre(genreString.toString());

    }

    @Override
    public void onRecyclerItemClick(int position) {
        view.startMovieDetailActivity(searchResult.get(position).getId());
    }

    @Override
    public void onLoadMore(int pageToLoad) {
        String cQuery = forQuery;
        view.showProgress();
        disposable.add(movieRepo.searchByQuery(cQuery, pageToLoad).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movieList -> {
                            if (!forQuery.equalsIgnoreCase(cQuery)) {
                                return;
                            }
                            searchResult.addAll(movieList.getMovies());
                            view.notifyAdapter();
                            view.hideProgress();
                        }
                )
        );
    }
}
