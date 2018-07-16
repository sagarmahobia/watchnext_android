package com.sagar.watchnext.activities.main.movies;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import java.util.List;

import javax.inject.Inject;

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

    private TmdbMovieRepo movieRepo;

    @Inject
    public Presenter(MoviesFragmentMvpContract.Model model, MoviesFragmentMvpContract.View view, TmdbMovieRepo movieRepo) {
        this.model = model;
        this.view = view;
        this.movieRepo = movieRepo;
        disposables = new CompositeDisposable();
    }

    @Override
    public void onCreate() {
//in Theaters

        disposables.add(movieRepo.getInTheaterMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    this.inTheatersMovies = movies.getMovies();
                    view.onSucceedLoadingMovieList(ListType.InTheaters);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.InTheaters);
                }));
//upcoming

        disposables.add(movieRepo.getUpcomingMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    this.upcomingMovies = movies.getMovies();
                    view.onSucceedLoadingMovieList(ListType.Upcoming);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.Upcoming);
                }));
//popular

        disposables.add(movieRepo.getPopularMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    this.popularMovies = movies.getMovies();
                    view.onSucceedLoadingMovieList(ListType.Popular);
                }, e -> {
                    view.onErrorLoadingMovieList(ListType.Popular);
                }));
//top rated

        disposables.add(movieRepo.getTopRatedMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    this.topRatedMovies = movies.getMovies();
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
        int movieId = getMovieListByType(listType).get(position).getId();
        view.startMovieDetailActivity(movieId);
    }

    @Override
    public int getCardsCount(ListType listType) {
        return getMovieListByType(listType).size();
    }

    @Override
    public void loadMore(ListType listType, int pageToLoad) {

        switch (listType) {
            case InTheaters:
                disposables.add(movieRepo.getInTheaterMovies(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(movies -> {
                            this.inTheatersMovies.addAll(movies.getMovies());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
            case Upcoming:
                disposables.add(movieRepo.getUpcomingMovies(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(movies -> {
                            this.upcomingMovies.addAll(movies.getMovies());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
            case Popular:
                disposables.add(movieRepo.getPopularMovies(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(movies -> {
                            this.popularMovies.addAll(movies.getMovies());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
            default://top rated
                disposables.add(movieRepo.getTopRatedMovies(pageToLoad).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(movies -> {
                            this.topRatedMovies.addAll(movies.getMovies());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
        }

    }
}
