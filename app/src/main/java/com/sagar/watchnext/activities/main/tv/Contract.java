package com.sagar.watchnext.activities.main.tv;

import android.arch.lifecycle.LifecycleObserver;

import com.sagar.watchnext.adapters.Card;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:14
 */
public class Contract {
    interface View {
        void showToast(String s);

        void onSucceedLoadingShowList(ListType listType);

        void onErrorLoadingShowList(ListType listType);

        void startTvDetailActivity(int tv_id);

        void notifyAdaptersNewData(ListType listType);

    }

    public interface Presenter extends LifecycleObserver {
        void load();

        //adapter calls
        void onBindCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

        int getCardsCount(ListType listType);

        void loadMore(ListType listType, int pageToLoad);
    }
}
