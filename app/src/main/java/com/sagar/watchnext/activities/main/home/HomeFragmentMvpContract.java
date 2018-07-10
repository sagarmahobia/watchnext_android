package com.sagar.watchnext.activities.main.home;

import com.sagar.watchnext.adapters.Card;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:09
 */
public interface HomeFragmentMvpContract {
    interface View {
        void showToast(String msg);

        //combined

        void onSucceedLoadingList(ListType listType);

        void onErrorLoadingList(ListType listType);

        void startMovieDetailActivity(int movieId);

        void startTvDetailActivity(int tv_id);

    }

    interface Presenter {
        //life cycle callback
        void onCreate();

        void onDestroy();


        //combined

        int getCardsCount(ListType listType);

        void onBindCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

    }

    interface Model {

    }
}
