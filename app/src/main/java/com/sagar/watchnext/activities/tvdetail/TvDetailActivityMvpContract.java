package com.sagar.watchnext.activities.tvdetail;

import com.sagar.watchnext.network.models.tv.details.Details;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:24
 */
public interface TvDetailActivityMvpContract {

    interface View {
        void onSucceedLoadingTvDetail(Details movieDetail);

        void onErrorLoadingMovieDetail();

        void showToast(String msg);
    }

    interface Presenter {
        void onCreate(int tvId);

        void onDestroy();
    }

    interface Model {
    }
}
