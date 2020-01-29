package com.sagar.watchnext.activities.main.movies;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.newmodels.CardItem;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 20:36
 */
public class MoviesFragmentViewModel extends ViewModel {

    private TmdbMovieRepo movieRepo;
    private TMDBRepository tmdbRepository;

    private List<CardModel> inTheatersMovies = new ArrayList<>();
    private List<CardModel> topRatedMovies = new ArrayList<>();
    private List<CardModel> popularMovies = new ArrayList<>();
    private List<CardModel> upcomingMovies = new ArrayList<>();

    private MutableLiveData<Response> inTheatersMoviesLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> topRatedMoviesLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> popularMoviesLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> upcomingMoviesLiveData = new MutableLiveData<>();

    private CardRecyclerModel inTheatersCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel topRatedCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel popularCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel upcomingCardRecyclerModel = new CardRecyclerModel();

    private CompositeDisposable disposables = new CompositeDisposable();

    MoviesFragmentViewModel(TmdbMovieRepo movieRepo, TMDBRepository tmdbRepository) {
        this.movieRepo = movieRepo;
        this.tmdbRepository = tmdbRepository;
        load();
    }

    MutableLiveData<Response> getInTheatersMoviesLiveData() {
        return inTheatersMoviesLiveData;
    }

    MutableLiveData<Response> getTopRatedMoviesLiveData() {
        return topRatedMoviesLiveData;
    }

    MutableLiveData<Response> getPopularMoviesLiveData() {
        return popularMoviesLiveData;
    }

    MutableLiveData<Response> getUpcomingMoviesLiveData() {
        return upcomingMoviesLiveData;
    }

    CardRecyclerModel getInTheatersCardRecyclerModel() {
        return inTheatersCardRecyclerModel;
    }

    CardRecyclerModel getTopRatedCardRecyclerModel() {
        return topRatedCardRecyclerModel;
    }

    CardRecyclerModel getPopularCardRecyclerModel() {
        return popularCardRecyclerModel;
    }

    CardRecyclerModel getUpcomingCardRecyclerModel() {
        return upcomingCardRecyclerModel;
    }


    public void load() {
//in Theaters
        disposables.add(tmdbRepository.getFirstPage("movie", "now_playing").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.inTheatersMovies.clear();
                            populateCardModels(inTheatersMovies, movies.getCardItems());
                            inTheatersMoviesLiveData.setValue(Response.success(this.inTheatersMovies));

                        }, e -> inTheatersMoviesLiveData.setValue(Response.error(e))
                ));
//upcoming
        disposables.add(tmdbRepository.getFirstPage("movie", "upcoming").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.upcomingMovies.clear();
                            populateCardModels(upcomingMovies, movies.getCardItems());
                            upcomingMoviesLiveData.setValue(Response.success(this.upcomingMovies));

                        }, e -> upcomingMoviesLiveData.setValue(Response.error(e))
                ));
//popular
        disposables.add(tmdbRepository.getFirstPage("movie", "popular").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.popularMovies.clear();
                            populateCardModels(popularMovies, movies.getCardItems());
                            popularMoviesLiveData.setValue(Response.success(this.popularMovies));

                        }, e -> popularMoviesLiveData.setValue(Response.error(e))
                ));

        disposables.add(tmdbRepository.getFirstPage("movie", "top_rated").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.topRatedMovies.clear();
                            populateCardModels(topRatedMovies, movies.getCardItems());
                            topRatedMoviesLiveData.setValue(Response.success(this.topRatedMovies));

                        }, e -> topRatedMoviesLiveData.setValue(Response.error(e))
                ));
    }

    private void populateCardModels(List<CardModel> cardModels, List<CardItem> cardItems) {
        for (CardItem cardItem : cardItems) {
            CardModel cardModel = new CardModel();
            cardModel.setId(cardItem.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(cardItem.getPosterPath()));
            cardModel.setTitle(cardItem.getTitle());
            cardModels.add(cardModel);
        }
    }

    @Override
    public void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
