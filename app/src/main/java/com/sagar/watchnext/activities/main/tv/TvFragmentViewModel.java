package com.sagar.watchnext.activities.main.tv;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.repo.TmdbTvRepo;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 17-Feb-19. at 00:45
 */
public class TvFragmentViewModel extends ViewModel {

    private TmdbTvRepo tvRepo;

    private List<CardModel> airingTodayShows;
    private List<CardModel> onTheAirShows;
    private List<CardModel> popularShows;
    private List<CardModel> topRatedShows;

    private MutableLiveData<Response> airingTodayShowsLiveData;
    private MutableLiveData<Response> onTheAirShowsLiveData;
    private MutableLiveData<Response> popularShowsLiveData;
    private MutableLiveData<Response> topRatedShowsLiveData;

    private CardRecyclerModel airingTodayShowsRecyclerModel;
    private CardRecyclerModel onTheAirShowsRecyclerModel;
    private CardRecyclerModel popularShowsRecyclerModel;
    private CardRecyclerModel topRatedShowsRecyclerModel;

    private CompositeDisposable disposables;

    TvFragmentViewModel(TmdbTvRepo tvRepo) {
        this.tvRepo = tvRepo;

        airingTodayShows = new ArrayList<>();
        onTheAirShows = new ArrayList<>();
        popularShows = new ArrayList<>();
        topRatedShows = new ArrayList<>();

        airingTodayShowsLiveData = new MutableLiveData<>();
        onTheAirShowsLiveData = new MutableLiveData<>();
        popularShowsLiveData = new MutableLiveData<>();
        topRatedShowsLiveData = new MutableLiveData<>();

        airingTodayShowsRecyclerModel = new CardRecyclerModel();
        onTheAirShowsRecyclerModel = new CardRecyclerModel();
        popularShowsRecyclerModel = new CardRecyclerModel();
        topRatedShowsRecyclerModel = new CardRecyclerModel();

        disposables = new CompositeDisposable();
        load();
    }

    MutableLiveData<Response> getAiringTodayShowsLiveData() {
        return airingTodayShowsLiveData;
    }

    MutableLiveData<Response> getOnTheAirShowsLiveData() {
        return onTheAirShowsLiveData;
    }

    MutableLiveData<Response> getPopularShowsLiveData() {
        return popularShowsLiveData;
    }

    MutableLiveData<Response> getTopRatedShowsLiveData() {
        return topRatedShowsLiveData;
    }

    CardRecyclerModel getAiringTodayShowsRecyclerModel() {
        return airingTodayShowsRecyclerModel;
    }

    CardRecyclerModel getOnTheAirShowsRecyclerModel() {
        return onTheAirShowsRecyclerModel;
    }

    CardRecyclerModel getPopularShowsRecyclerModel() {
        return popularShowsRecyclerModel;
    }

    CardRecyclerModel getTopRatedShowsRecyclerModel() {
        return topRatedShowsRecyclerModel;
    }

    public void load() {
        //airingToday
        disposables.add(tvRepo.getAiringToday().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.airingTodayShows.clear();
                    populateCardModels(airingTodayShows, shows.getShows());
                    airingTodayShowsLiveData.setValue(Response.success(airingTodayShows));

                }, e -> airingTodayShowsLiveData.setValue(Response.error(e))));

        //on the air
        disposables.add(tvRepo.getOnTheAir().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.onTheAirShows.clear();
                    populateCardModels(onTheAirShows, shows.getShows());
                    onTheAirShowsLiveData.setValue(Response.success(onTheAirShows));

                }, e -> onTheAirShowsLiveData.setValue(Response.error(e))));

        //popular
        disposables.add(tvRepo.getPopular().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.popularShows.clear();
                    populateCardModels(popularShows, shows.getShows());
                    popularShowsLiveData.setValue(Response.success(popularShows));

                }, e -> popularShowsLiveData.setValue(Response.error(e))));

        //top rated
        disposables.add(tvRepo.getTopRated().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.topRatedShows.clear();
                    populateCardModels(topRatedShows, shows.getShows());
                    topRatedShowsLiveData.setValue(Response.success(topRatedShows));

                }, e -> topRatedShowsLiveData.setValue(Response.error(e))));
    }

    private void populateCardModels(List<CardModel> cardModels, List<Show> shows) {
        for (Show show : shows) {
            CardModel cardModel = new CardModel();
            cardModel.setId(show.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(show.getPosterPath()));
            cardModel.setTitle(show.getName());
            cardModels.add(cardModel);
        }
    }

    @Override
    public void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    void loadMoreAiringToday(int pageToLoad) {
        disposables.add(tvRepo.getAiringToday(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    populateCardModels(airingTodayShows, shows.getShows());
                    airingTodayShowsLiveData.setValue(Response.success(airingTodayShows));
                }));
    }

    void loadMoreOnTheAir(int pageToLoad) {
        disposables.add(tvRepo.getOnTheAir(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    populateCardModels(onTheAirShows, shows.getShows());
                    onTheAirShowsLiveData.setValue(Response.success(onTheAirShows));
                }));
    }

    void loadMorePopular(int pageToLoad) {
        disposables.add(tvRepo.getPopular(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    populateCardModels(popularShows, shows.getShows());
                    popularShowsLiveData.setValue(Response.success(popularShows));
                }));
    }

    void loadMoreTopRated(int pageToLoad) {
        disposables.add(tvRepo.getTopRated(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {
                    populateCardModels(topRatedShows, shows.getShows());
                    topRatedShowsLiveData.setValue(Response.success(topRatedShows));
                }));
    }
}
