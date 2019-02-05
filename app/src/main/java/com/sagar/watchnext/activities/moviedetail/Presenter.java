package com.sagar.watchnext.activities.moviedetail;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:23
 */
@MovieDetailActivityScope
public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private TmdbMovieRepo movieRepo;

    private CompositeDisposable disposable;

    @Inject
    public Presenter(Contract.View view, TmdbMovieRepo movieRepo) {
        this.view = view;
        this.movieRepo = movieRepo;
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
                subscribe(movieDetail -> view.onSucceedLoadingMovieDetail(movieDetail),
                        error -> view.onErrorLoadingMovieDetail()));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }
}
