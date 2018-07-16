package com.sagar.watchnext.activities.tvdetail;

import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

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

    private TmdbTvRepo tvRepo;

    @Inject
    public Presenter(TvDetailActivityMvpContract.Model model, TvDetailActivityMvpContract.View view, TmdbTvRepo tvRepo) {
        this.model = model;
        this.view = view;
        this.tvRepo = tvRepo;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(int tvId) {

        disposable.add(tvRepo.getDetails(tvId).
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
