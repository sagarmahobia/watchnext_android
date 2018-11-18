package com.sagar.watchnext.activities.tvdetail;

import android.arch.lifecycle.LifecycleObserver;

import com.sagar.watchnext.network.models.tv.details.Details;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:24
 */
public interface Contract {

    interface View {
        int getTvId();

        void onSucceedLoadingTvDetail(Details movieDetail);

        void onErrorLoadingMovieDetail();

        void showToast(String msg);
    }

    interface Presenter extends LifecycleObserver {
        void load();
    }

}
