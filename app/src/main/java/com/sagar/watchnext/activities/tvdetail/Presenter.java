package com.sagar.watchnext.activities.tvdetail;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:26
 */
@TvDetailActivityScope
public class Presenter implements Contract.Presenter {

    @Inject
    Contract.View view;

    @Inject
    TmdbTvRepo tvRepo;

    private CompositeDisposable disposable;

    @Inject
    public Presenter(TvDetailActivityComponent component) {
        component.inject(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        disposable = new CompositeDisposable();
        load();
    }

    @Override
    public void load() {
        int tvId = view.getTvId();
        disposable.add(tvRepo.getDetails(tvId).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(details -> view.onSucceedLoadingTvDetail(details),
                        error -> view.onErrorLoadingMovieDetail()));

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }
}
