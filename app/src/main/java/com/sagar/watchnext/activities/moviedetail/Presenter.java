package com.sagar.watchnext.activities.moviedetail;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

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
public class Presenter implements Contract.Presenter {

    @Inject
    Contract.View view;

    @Inject
    TmdbMovieRepo movieRepo;

    private List<Movie> recommendations;
    private List<Movie> similars;
    private List<Video> videos;

    private CompositeDisposable disposable;

    @Inject
    public Presenter(MovieDetailActivityComponent component) {
        component.inject(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        disposable = new CompositeDisposable();
        load();
    }

    @Override
    public void load() {
        int movieId = view.getMovieId();
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

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }
}
