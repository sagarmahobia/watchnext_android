package com.sagar.watchnext.screens.moviedetailactivity;

import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:17
 */
public interface MovieDetailActivityMvpContract {

    interface View {
        void onSucceedLoadingMovieDetail(MovieDetail movieDetail);

        void onErrorLoadingMovieDetail();
        void showToast(String msg);
    }

    interface Presenter {
        void onCreate(int movieId);

        void onDestroy();

    }

    interface Model {

    }

}


