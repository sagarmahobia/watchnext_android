package com.sagar.watchnext.activities.main.tv;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.newmodels.CardItem;
import com.sagar.watchnext.network.repo.TMDBRepository;
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
    private TMDBRepository tmdbRepository;

    private List<CardModel> airingTodayShows = new ArrayList<>();
    private List<CardModel> onTheAirShows = new ArrayList<>();
    private List<CardModel> popularShows = new ArrayList<>();
    private List<CardModel> topRatedShows = new ArrayList<>();

    private MutableLiveData<Response> airingTodayShowsLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> onTheAirShowsLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> popularShowsLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> topRatedShowsLiveData = new MutableLiveData<>();

    private CardRecyclerModel airingTodayShowsRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel onTheAirShowsRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel popularShowsRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel topRatedShowsRecyclerModel = new CardRecyclerModel();

    private CompositeDisposable disposables = new CompositeDisposable();

    TvFragmentViewModel(TmdbTvRepo tvRepo, TMDBRepository tmdbRepository) {
        this.tvRepo = tvRepo;
        this.tmdbRepository = tmdbRepository;

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
        disposables.add(tmdbRepository.getFirstPage("tv", "airing_today").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.airingTodayShows.clear();
                    populateCardModels(airingTodayShows, shows.getCardItems());
                    airingTodayShowsLiveData.setValue(Response.success(airingTodayShows));

                }, e -> airingTodayShowsLiveData.setValue(Response.error(e))));

        //on the air
        disposables.add(tmdbRepository.getFirstPage("tv", "on_the_air").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.onTheAirShows.clear();
                    populateCardModels(onTheAirShows, shows.getCardItems());
                    onTheAirShowsLiveData.setValue(Response.success(onTheAirShows));

                }, e -> onTheAirShowsLiveData.setValue(Response.error(e))));

        //popular
        disposables.add(tmdbRepository.getFirstPage("tv", "popular").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.popularShows.clear();
                    populateCardModels(popularShows, shows.getCardItems());
                    popularShowsLiveData.setValue(Response.success(popularShows));

                }, e -> popularShowsLiveData.setValue(Response.error(e))));

        //top rated
        disposables.add(tmdbRepository.getFirstPage("tv", "top_rated").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(shows -> {

                    this.topRatedShows.clear();
                    populateCardModels(topRatedShows, shows.getCardItems());
                    topRatedShowsLiveData.setValue(Response.success(topRatedShows));

                }, e -> topRatedShowsLiveData.setValue(Response.error(e))));
    }

    private void populateCardModels(List<CardModel> cardModels, List<CardItem> shows) {
        for (CardItem show : shows) {
            CardModel cardModel = new CardModel();
            cardModel.setId(show.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(show.getPosterPath()));
            cardModel.setTitle(show.getTitle());
            cardModels.add(cardModel);
        }
    }

    @Override
    public void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

}
