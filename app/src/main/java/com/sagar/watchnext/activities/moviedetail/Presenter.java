package com.sagar.watchnext.activities.moviedetail;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.videos.Video;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:23
 */
@MovieDetailActivityScope
public class Presenter implements MovieDetailActivityMvpContract.Presenter {

    private MovieDetailActivityMvpContract.View view;
    private MovieDetailActivityMvpContract.Model model;
    private CompositeDisposable disposable;

    private List<Movie> recommendations;
    private List<Movie> similars;
    private List<Video> videos;

    private TmdbMovieRepo movieRepo;

    @Inject
    public Presenter(MovieDetailActivityMvpContract.View view, MovieDetailActivityMvpContract.Model model, TmdbMovieRepo movieRepo) {
        this.view = view;
        this.model = model;
        this.movieRepo = movieRepo;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(int movieId) {

        disposable.add(movieRepo.getMovieDetail(movieId).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movieDetail -> {
                    view.onSucceedLoadingMovieDetail(movieDetail);
                }, error -> {
                    view.onErrorLoadingMovieDetail();
                }));
 /*
        disposable.add(movieRepo.getRecommendations(movieId).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(recommendations -> {
                    this.recommendations = recommendations.getMovies();
                    //todo notify view
                }, error -> {
                    //todo notify view
                })
        );

        disposable.add(movieRepo.getSimilars(movieId).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(similars -> {
                    this.similars = similars.getMovies();
                    //todo notify view
                }, error -> {
                    //todo notify view
                })
        );

        disposable.add(movieRepo.getVideos(movieId).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(videos -> {
                    this.videos = videos.getVideos();
                    //todo notify view
                }, error -> {
                    //todo notify view
                })
        );

 */
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
