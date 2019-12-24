package com.sagar.watchnext.activities.main.home;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
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
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:47
 */
public class HomeFragmentViewModel extends ViewModel {

    private TmdbMovieRepo movieRepo;
    private TmdbTvRepo tvRepo;

    private List<CardModel> movies;
    private List<CardModel> shows;

    private MutableLiveData<Response> moviesLiveData;
    private MutableLiveData<Response> showsLiveData;

    private CardRecyclerModel moviesCardRecyclerModel;
    private CardRecyclerModel tvShowsCardRecyclerModel;

    private CompositeDisposable disposables;

    HomeFragmentViewModel(TmdbMovieRepo movieRepo, TmdbTvRepo tvRepo) {
        this.movieRepo = movieRepo;
        this.tvRepo = tvRepo;

        movies = new ArrayList<>();
        shows = new ArrayList<>();

        moviesLiveData = new MutableLiveData<>();
        showsLiveData = new MutableLiveData<>();

        moviesCardRecyclerModel = new CardRecyclerModel();
        tvShowsCardRecyclerModel = new CardRecyclerModel();

        disposables = new CompositeDisposable();
        load();
    }

    MutableLiveData<Response> getMoviesLiveData() {
        return moviesLiveData;
    }

    MutableLiveData<Response> getShowsLiveData() {
        return showsLiveData;
    }

    CardRecyclerModel getMoviesCardRecyclerModel() {
        return moviesCardRecyclerModel;
    }

    CardRecyclerModel getTvShowsCardRecyclerModel() {
        return tvShowsCardRecyclerModel;
    }

    public void load() {
        disposables.add(movieRepo.getInTheaterMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                            this.movies.clear();
                            for (Movie movie : movies.getMovies()) {
                                CardModel cardModel = new CardModel();
                                cardModel.setId(movie.getId());
                                cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(movie.getPosterPath()));
                                cardModel.setTitle(movie.getTitle());
                                this.movies.add(cardModel);
                            }
                            moviesLiveData.setValue(Response.success(this.movies));
                        }, e -> moviesLiveData.setValue(Response.error(e))
                ));


        disposables.add(tvRepo.getOnTheAir().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> {
                            this.shows.clear();
                            for (Show show : shows.getShows()) {
                                CardModel cardModel = new CardModel();
                                cardModel.setId(show.getId());
                                cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(show.getPosterPath()));
                                cardModel.setTitle(show.getName());
                                this.shows.add(cardModel);
                            }
                            showsLiveData.setValue(Response.success(this.shows));
                        }, e -> showsLiveData.setValue(Response.error(e))
                ));

    }

    @Override
    public void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    void loadMoreMovies(int pageToLoad) {
        disposables.add(movieRepo.getInTheaterMovies(pageToLoad).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movieList -> {
                    for (Movie movie : movieList.getMovies()) {
                        CardModel cardModel = new CardModel();
                        cardModel.setId(movie.getId());
                        cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(movie.getPosterPath()));
                        cardModel.setTitle(movie.getTitle());
                        this.movies.add(cardModel);
                    }
                    moviesLiveData.setValue(Response.success(this.movies));
                }));
    }

    void loadMoreTvShows(int pageToLoad) {
        disposables.add(tvRepo.getOnTheAir(pageToLoad).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(tvList -> {
                    for (Show show : tvList.getShows()) {
                        CardModel cardModel = new CardModel();
                        cardModel.setId(show.getId());
                        cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(show.getPosterPath()));
                        cardModel.setTitle(show.getName());
                        this.shows.add(cardModel);
                    }
                    showsLiveData.setValue(Response.success(shows));
                }));
    }

}
