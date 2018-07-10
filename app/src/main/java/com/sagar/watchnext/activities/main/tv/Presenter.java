package com.sagar.watchnext.activities.main.tv;

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
public class Presenter implements TvFragmentMvpContract.Presenter {
    private TvFragmentMvpContract.Model model;
    private TvFragmentMvpContract.View view;
    private CompositeDisposable disposables;

    private List<Show> airingTodayShows;
    private List<Show> onTheAirShows;
    private List<Show> popularShows;
    private List<Show> topRatedShows;

    private TmdbTvRepo tvRepo;

    @Inject
    public Presenter(TvFragmentMvpContract.Model model, TvFragmentMvpContract.View view, TmdbTvRepo tvRepo) {
        this.model = model;
        this.view = view;
        this.tvRepo = tvRepo;
        disposables = new CompositeDisposable();
    }

    @Override
    public void onCreate() {

        //airingToday
        disposables.add(tvRepo.getAiringToday().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    //todo handle null movies
                    this.airingTodayShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.AiringToday);
                }, e -> {
                    view.onErrorLoadingShowList(ListType.AiringToday);
                }));

        //on the air
        disposables.add(tvRepo.getOnTheAir().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    //todo handle null movies
                    this.onTheAirShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.OnTheAir);

                }, e -> {
                    view.onErrorLoadingShowList(ListType.OnTheAir);
                }));

        //popular
        disposables.add(tvRepo.getPopular().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    //todo handle null movies
                    this.popularShows = shows.getShows();
                    view.onSucceedLoadingShowList(ListType.Popular);

                }, e -> {
                    view.onErrorLoadingShowList(ListType.Popular);

                }));

        //top rated
        disposables.add(tvRepo.getTopRated().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    //todo handle null movies
                    this.topRatedShows = shows.getShows();
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

        int tvId = getListByType(listType).get(position).getId();
        view.startTvDetailActivity(tvId);

    }

    @Override
    public int getCardsCount(ListType listType) {
        return getListByType(listType).size();

    }
}
