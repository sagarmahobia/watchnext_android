package com.sagar.watchnext.screens.tv;

import com.sagar.watchnext.adapters.Card;
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
                    view.onSucceedLoadingShowList(ListType.AiringToday);
                }, e -> {
                    view.onErrorLoadingShowList(ListType.AiringToday);
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
                    view.onSucceedLoadingShowList(ListType.OnTheAir);

                }, e -> {
                    view.onErrorLoadingShowList(ListType.OnTheAir);
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
                    view.onSucceedLoadingShowList(ListType.Popular);

                }, e -> {
                    view.onErrorLoadingShowList(ListType.Popular);

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
                    view.onSucceedLoadingShowList(ListType.TopRated);
                }, e -> {
                    view.onErrorLoadingShowList(ListType.TopRated);
                }));


    }

    @Override
    public void onDestroy() {
        disposables.dispose();
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
        //todo modify on click listener
        String name = getListByType(listType).get(position).getName();
        view.showToast(name + " was clicked");
    }

    @Override
    public int getCardsCount(ListType listType) {
        return getListByType(listType).size();

    }
}
