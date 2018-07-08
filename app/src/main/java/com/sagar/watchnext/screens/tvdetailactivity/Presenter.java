package com.sagar.watchnext.screens.tvdetailactivity;

import com.sagar.watchnext.network.models.people.detail.Detail;
import com.sagar.watchnext.network.models.tv.details.Details;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:26
 */
@TvDetailActivityScope
public class Presenter implements TvDetailActivityMvpContract.Presenter {

    private TvDetailActivityMvpContract.Model model;
    private TvDetailActivityMvpContract.View view;
    private CompositeDisposable disposable;

    @Inject
    public Presenter(TvDetailActivityMvpContract.Model model, TvDetailActivityMvpContract.View view) {
        this.model = model;
        this.view = view;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(int tvId) {
        Single<Details> detailsSingle = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getTvDetail(tvId));
            } catch (IOException error) {
                emitter.onError(error);
            }
        });
        disposable.add(detailsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(details -> {
                    view.onSucceedLoadingTvDetail(details);
                }, error -> {
                    view.onErrorLoadingMovieDetail();
                }));

    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
