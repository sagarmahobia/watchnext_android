package com.sagar.watchnext.activities.main.movies;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.models.movies.Movie;
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

    private List<CardModel> inTheatersMovies;
    private List<CardModel> topRatedMovies;
    private List<CardModel> popularMovies;
    private List<CardModel> upcomingMovies;

    private MutableLiveData<Response> inTheatersMoviesLiveData;
    private MutableLiveData<Response> topRatedMoviesLiveData;
    private MutableLiveData<Response> popularMoviesLiveData;
    private MutableLiveData<Response> upcomingMoviesLiveData;

    private CardRecyclerModel inTheatersCardRecyclerModel;
    private CardRecyclerModel topRatedCardRecyclerModel;
    private CardRecyclerModel popularCardRecyclerModel;
    private CardRecyclerModel upcomingCardRecyclerModel;

    private CompositeDisposable disposables;

    MoviesFragmentViewModel(TmdbMovieRepo movieRepo) {
        this.movieRepo = movieRepo;

        inTheatersMovies = new ArrayList<>();
        topRatedMovies = new ArrayList<>();
        popularMovies = new ArrayList<>();
        upcomingMovies = new ArrayList<>();

        inTheatersMoviesLiveData = new MutableLiveData<>();
        topRatedMoviesLiveData = new MutableLiveData<>();
        popularMoviesLiveData = new MutableLiveData<>();
        upcomingMoviesLiveData = new MutableLiveData<>();

        inTheatersCardRecyclerModel = new CardRecyclerModel();
        topRatedCardRecyclerModel = new CardRecyclerModel();
        popularCardRecyclerModel = new CardRecyclerModel();
        upcomingCardRecyclerModel = new CardRecyclerModel();

        disposables = new CompositeDisposable();
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
        disposables.add(movieRepo.getInTheaterMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.inTheatersMovies.clear();
                            populateCardModels(inTheatersMovies, movies.getMovies());
                            inTheatersMoviesLiveData.setValue(Response.success(this.inTheatersMovies));

                        }, e -> inTheatersMoviesLiveData.setValue(Response.error(e))
                ));
//upcoming
        disposables.add(movieRepo.getUpcomingMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.upcomingMovies.clear();
                            populateCardModels(upcomingMovies, movies.getMovies());
                            upcomingMoviesLiveData.setValue(Response.success(this.upcomingMovies));

                        }, e -> upcomingMoviesLiveData.setValue(Response.error(e))
                ));
//popular
        disposables.add(movieRepo.getPopularMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.popularMovies.clear();
                            populateCardModels(popularMovies, movies.getMovies());
                            popularMoviesLiveData.setValue(Response.success(this.popularMovies));

                        }, e -> popularMoviesLiveData.setValue(Response.error(e))
                ));
//top rated
        disposables.add(movieRepo.getTopRatedMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {

                            this.topRatedMovies.clear();
                            populateCardModels(topRatedMovies, movies.getMovies());
                            topRatedMoviesLiveData.setValue(Response.success(this.topRatedMovies));

                        }, e -> topRatedMoviesLiveData.setValue(Response.error(e))
                ));
    }

    void loadMoreInTheatersMovies(int pageToLoad) {
        disposables.add(movieRepo.getInTheaterMovies(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    populateCardModels(inTheatersMovies, movies.getMovies());
                    inTheatersMoviesLiveData.setValue(Response.success(this.inTheatersMovies));
                }));
    }

    void loadMoreUpcomingMovies(int pageToLoad) {
        disposables.add(movieRepo.getUpcomingMovies(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    populateCardModels(upcomingMovies, movies.getMovies());
                    upcomingMoviesLiveData.setValue(Response.success(this.upcomingMovies));
                }));
    }

    void loadMorePopularMovies(int pageToLoad) {
        disposables.add(movieRepo.getPopularMovies(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    populateCardModels(popularMovies, movies.getMovies());
                    popularMoviesLiveData.setValue(Response.success(popularMovies));
                }));
    }

    void loadMoreTopRatedMovies(int pageToLoad) {
        disposables.add(movieRepo.getTopRatedMovies(pageToLoad).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    populateCardModels(topRatedMovies, movies.getMovies());
                    topRatedMoviesLiveData.setValue(Response.success(topRatedMovies));
                }));
    }

    private void populateCardModels(List<CardModel> cardModels, List<Movie> movies) {
        for (Movie movie : movies) {
            CardModel cardModel = new CardModel();
            cardModel.setId(movie.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(movie.getPosterPath()));
            cardModel.setTitle(movie.getTitle());
            cardModels.add(cardModel);
        }
    }

    @Override
    public void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
