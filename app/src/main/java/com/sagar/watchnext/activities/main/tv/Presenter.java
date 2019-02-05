package com.sagar.watchnext.activities.main.tv;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:15
 */
@TvFragmentScope
public class Presenter implements Contract.Presenter {


    private Contract.View view;
    private TmdbTvRepo tvRepo;

    private List<Show> airingTodayShows;
    private List<Show> onTheAirShows;
    private List<Show> popularShows;
    private List<Show> topRatedShows;

    private CompositeDisposable disposables;

    @Inject
    public Presenter(Contract.View view, TmdbTvRepo tvRepo) {
        this.view = view;
        this.tvRepo = tvRepo;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        disposables = new CompositeDisposable();
        load();
    }

    @Override
    public void load() {
        //airingToday
        disposables.add(tvRepo.getAiringToday().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    this.airingTodayShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.AiringToday);
                }, e -> {
                    view.onErrorLoadingShowList(ListType.AiringToday);
                }));

        //on the air
        disposables.add(tvRepo.getOnTheAir().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    this.onTheAirShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.OnTheAir);

                }, e -> {
                    view.onErrorLoadingShowList(ListType.OnTheAir);
                }));

        //popular
        disposables.add(tvRepo.getPopular().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    this.popularShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.Popular);

                }, e -> {
                    view.onErrorLoadingShowList(ListType.Popular);

                }));

        //top rated
        disposables.add(tvRepo.getTopRated().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    this.topRatedShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.TopRated);
                }, e -> {
                    view.onErrorLoadingShowList(ListType.TopRated);
                }));


    }

    private List<Show> getListByType(ListType listType) {
        switch (listType) {
            case AiringToday:
                return airingTodayShows;
            case OnTheAir:
                return onTheAirShows;
            case Popular:
                return popularShows;
            default://top Rated
                return topRatedShows;
        }
    }

    @Override
    public void onBindCard(ListType listType, Card card, int position) {
        Show show = getListByType(listType).get(position);
        card.setTitle(show.getName());
        card.setImage(show.getPosterPath());
    }

    @Override
    public void onRecyclerItemClick(ListType listType, int position) {

        int tvId = getListByType(listType).get(position).getId();
        view.startTvDetailActivity(tvId);

    }

    @Override
    public int getCardsCount(ListType listType) {
        return getListByType(listType).size();

    }

    @Override
    public void loadMore(ListType listType, int pageToLoad) {
        switch (listType) {
            case AiringToday:
                disposables.add(tvRepo.getAiringToday(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(shows -> {
                            this.airingTodayShows.addAll(shows.getShows());
                            view.notifyAdaptersNewData(listType);
                        }));

                break;
            case OnTheAir:
                disposables.add(tvRepo.getOnTheAir(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(shows -> {
                            this.onTheAirShows.addAll(shows.getShows());
                            view.notifyAdaptersNewData(listType);

                        }));

                break;
            case Popular:
                disposables.add(tvRepo.getPopular(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(shows -> {
                            this.popularShows.addAll(shows.getShows());
                            view.notifyAdaptersNewData(listType);

                        }));
                break;
            case TopRated:
                disposables.add(tvRepo.getTopRated(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(shows -> {
                            this.topRatedShows.addAll(shows.getShows());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposables.dispose();
    }

}
