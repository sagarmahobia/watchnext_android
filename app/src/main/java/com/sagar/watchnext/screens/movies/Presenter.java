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
                    view.onSucceedLoadingMovieList(ListType.InTheaters);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.InTheaters);
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
                    view.onSucceedLoadingMovieList(ListType.Upcoming);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.Upcoming);
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
                    view.onSucceedLoadingMovieList(ListType.Popular);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.Popular);
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
                    view.onSucceedLoadingMovieList(ListType.TopRated);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.TopRated);
                }));


    }

    @Override
    public void onDestroy() {
        disposables.dispose();
    }

    private List<Movie> getMovieListByType(ListType listType) {
        switch (listType) {
            case InTheaters:
                return inTheatersMovies;
            case Upcoming:
                return upcomingMovies;
            case Popular:
                return popularMovies;
            default://top rated
                return topRatedMovies;
        }
    }

    @Override
    public void onBindMovieCard(ListType listType, Card card, int position) {
        Movie movie = getMovieListByType(listType).get(position);
        card.setTitle(movie.getTitle());
        card.setImage(movie.getPosterPath());
    }

    @Override
    public void onRecyclerItemClick(ListType listType, int position) {
        String title = getMovieListByType(listType).get(position).getTitle();
        view.showToast(title + " was clicked");
        //todo modify on click listener
    }

    @Override
    public int getCardsCount(ListType listType) {
        return getMovieListByType(listType).size();
    }
}
