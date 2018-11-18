package com.sagar.watchnext.activities.main.home;

import android.arch.lifecycle.LifecycleObserver;

import com.sagar.watchnext.adapters.Card;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:09
 */
public interface Contract {
    interface View {
        void showToast(String msg);

        void onSucceedLoadingList(ListType listType);

        void onErrorLoadingList(ListType listType);

        void startMovieDetailActivity(int movieId);

        void startTvDetailActivity(int tv_id);

        void notifyAdaptersNewData(ListType listType);
    }

    interface Presenter extends LifecycleObserver {
        //life cycle callback
        void load();

        void loadMore(ListType listType, int pageToLoad);

        //adapter calls
        int getCardsCount(ListType listType);

        void onBindCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

    }

}
