package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.movies.Movie;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:08
 */

@MoviesFragmentScope
public class Presenter implements MoviesFragmentMvpContract.Presenter {
    private MoviesFragmentMvpContract.Model model;
    private MoviesFragmentMvpContract.View view;
    private List<Movie> inTheatersMovies;
    private List<Movie> topRatedMovies;
    private List<Movie> popularMovies;
    private List<Movie> upcomingMovies;

    private CompositeDisposable disposables;


    @Inject
    public Presenter(MoviesFragmentMvpContract.Model model, MoviesFragmentMvpContract.View view) {
        this.model = model;
        this.view = view;
        disposables = new CompositeDisposable();
    }

    @Override
    public void onCreate() {
//in Theaters
        Single<List<Movie>> singleInTheatersMovie = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getInTheaterMovies());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleInTheatersMovie.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.inTheatersMovies = movies;
                    view.onSucceedLoadingInTheatersMovieList();
                }, e -> {
                    view.onErrorLoadingInTheatersMovieList();
                }));
//upcoming
        Single<List<Movie>> singleUpcoming = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getUpcomingMovies());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleUpcoming.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.upcomingMovies = movies;
                     view.onSucceedLoadingUpcomingMovieList();
                }, e -> {
                     view.onErrorLoadingUpcomingMovieList();
                }));
//popular
        Single<List<Movie>> singlePopularMovies = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getPopularMovies());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singlePopularMovies.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.popularMovies = movies;
                    view.onSucceedLoadingPopularMovieList();
                }, e -> {
                    view.onErrorLoadingPopularMovieList();
                }));
//top rated
        Single<List<Movie>> singleTopRated = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getTopRatedMovies());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleTopRated.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    //todo handle null movies
                    this.topRatedMovies = movies;
                     view.onSucceedLoadingTopRatedMovieList();
                }, e -> {
                    view.onErrorLoadingTopRatedMovieList();
                }));


    }

    @Override
    public void onDestroy() {
        disposables.dispose();
    }

    //In Theater
    @Override
    public void onBindInTheatersMovieCard(Card card, int position) {
        Movie movie = inTheatersMovies.get(position);
        card.setTitle(movie.getTitle());
        card.setImage(movie.getPosterPath());
    }

    @Override
    public void onInTheatersRecyclerItemClick(int position) {
        //todo modify on click listener
        view.showToast(inTheatersMovies.get(position).getTitle() + " was clicked");

    }

    @Override
    public int getInTheatersCardsCount() {
        return inTheatersMovies.size();
    }

    @Override
    public void onBindUpcomingMovieCard(Card card, int position) {
        Movie movie = upcomingMovies.get(position);
        card.setTitle(movie.getTitle());
        card.setImage(movie.getPosterPath());
    }

    @Override
    public void onUpcomingRecyclerItemClick(int position) {
        //todo modify on click listener
        view.showToast(upcomingMovies.get(position).getTitle() + " was clicked");

    }

    @Override
    public int getUpComingCardsCount() {
        return upcomingMovies.size();
    }

    @Override
    public void onBindPopularMovieCard(Card card, int position) {
        Movie movie = popularMovies.get(position);
        card.setTitle(movie.getTitle());
        card.setImage(movie.getPosterPath());
    }

    @Override
    public void onPopularRecyclerItemClick(int position) {
        //todo modify on click listener
        view.showToast(popularMovies.get(position).getTitle() + " was clicked");

    }

    @Override
    public int getPopularCardsCount() {
        return popularMovies.size();
    }

    @Override
    public void onBindTopRatedMovieCard(Card card, int position) {
        Movie movie = topRatedMovies.get(position);
        card.setTitle(movie.getTitle());
        card.setImage(movie.getPosterPath());
    }

    @Override
    public void onTopRatedRecyclerItemClick(int position) {
        //todo modify on click listener
        view.showToast(topRatedMovies.get(position).getTitle() + " was clicked");

    }

    @Override
    public int getTopRatedCardsCount() {
        return topRatedMovies.size();
    }
}
