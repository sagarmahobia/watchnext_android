package com.sagar.watchnext.activities.main.home;

import com.sagar.watchnext.adapters.Card;

import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:09
 */
public interface HomeFragmentMvpContract {
    interface View {
        void showToast(String msg);

        void onSucceedLoadingList(ListType listType);

        void onErrorLoadingList(ListType listType);

        void startMovieDetailActivity(int movieId);

        void startTvDetailActivity(int tv_id);

        void notifyAdaptersNewData(ListType listType);
    }

    interface Presenter {
        //life cycle callback
        void onCreate();

        void loadMore(ListType listType, int pageToLoad);

        void onDestroy();

        //adapter calls
        int getCardsCount(ListType listType);

        void onBindCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

    }

    interface Model {

    }
}
