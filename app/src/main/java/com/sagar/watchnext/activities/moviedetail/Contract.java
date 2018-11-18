package com.sagar.watchnext.activities.moviedetail;

import android.arch.lifecycle.LifecycleObserver;

import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:17
 */
public interface Contract {

    interface View {
        int getMovieId();

        void onSucceedLoadingMovieDetail(MovieDetail movieDetail);

        void onErrorLoadingMovieDetail();

        void showToast(String msg);
    }

    interface Presenter extends LifecycleObserver {
        void load();
    }

}


