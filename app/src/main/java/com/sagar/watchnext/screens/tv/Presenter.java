package com.sagar.watchnext.screens.tv;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.network.models.tv.Show;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:15
 */
@TvFragmentScope
public class Presenter implements TvFragmentMvpContract.Presenter {
    private TvFragmentMvpContract.Model model;
    private TvFragmentMvpContract.View view;
    private CompositeDisposable disposables;

    private List<Show> airingTodayShows;
    private List<Show> onTheAirShows;
    private List<Show> popularShows;
    private List<Show> topRatedShows;

    @Inject
    public Presenter(TvFragmentMvpContract.Model model, TvFragmentMvpContract.View view) {
        this.model = model;
        this.view = view;
        disposables = new CompositeDisposable();
    }

    @Override
    public void onCreate() {
        //airingToday
        Single<List<Show>> singleAiringTodayShow = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getAiringToday());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleAiringTodayShow.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.airingTodayShows = movies;
                    view.onSucceedLoadingAiringTodayShowList();
                }, e -> {
                    view.onErrorLoadingAiringTodayShowList();
                }));

        //on the air
        Single<List<Show>> singleOnTheAirShow = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getOnTheAir());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleOnTheAirShow.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.onTheAirShows = movies;
                    view.onSucceedLoadingOnTheAirShowList();
                }, e -> {
                   view.onErrorLoadingOnTheAirShowList();
                }));

        //popular
        Single<List<Show>> singlePopularShow = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getPopular());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singlePopularShow.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.popularShows = movies;
                     view.onSucceedLoadingPopularList();
                }, e -> {
                    view.onErrorLoadingPopularList();
                }));  //popular

//top rated
        Single<List<Show>> singleTopRatedShow = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getTopRated());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleTopRatedShow.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.topRatedShows = movies;
                     view.onSucceedLoadingTopRatedShowList();
                }, e -> {
                    view.onErrorLoadingTopRatedShowList();
                }));


    }

    @Override
    public void onDestroy() {
        disposables.dispose();
    }

    @Override
    public void onBindAiringTodayShowCard(Card card, int position) {
        Show show = airingTodayShows.get(position);
        card.setTitle(show.getName());
        card.setImage(show.getPosterPath());
    }

    @Override
    public void onAiringTodayRecyclerItemClick(int position) {
        view.showToast(airingTodayShows.get(position).getName() + " was clicked");

    }

    @Override
    public int getAiringTodayCardsCount() {
        return airingTodayShows.size();
    }

    @Override
    public void onBindOnTheAirShowCard(Card card, int position) {
        Show show = onTheAirShows.get(position);
        card.setTitle(show.getName());
        card.setImage(show.getPosterPath());

    }

    @Override
    public void onOnTheAirRecyclerItemClick(int position) {
        view.showToast(onTheAirShows.get(position).getName() + " was clicked");
    }

    @Override
    public int getOnTheAirCardsCount() {
        return onTheAirShows.size();
    }

    @Override
    public void onBindPopularShowCard(Card card, int position) {
        Show show = popularShows.get(position);
        card.setTitle(show.getName());
        card.setImage(show.getPosterPath());
    }

    @Override
    public void onPopularRecyclerItemClick(int position) {
        view.showToast(popularShows.get(position).getName() + " was clicked");

    }

    @Override
    public int getPopularCardsCount() {
        return popularShows.size();

    }

    @Override
    public void onBindTopRatedShowCard(Card card, int position) {
        Show show = topRatedShows.get(position);
        card.setTitle(show.getName());
        card.setImage(show.getPosterPath());
    }

    @Override
    public void onTopRatedRecyclerItemClick(int position) {
        view.showToast(topRatedShows.get(position).getName() + " was clicked");

    }

    @Override
    public int getTopRatedCardsCount() {
        return topRatedShows.size();
    }
}
