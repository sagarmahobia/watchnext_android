package com.sagar.watchnext.activities.main.tv;

import com.sagar.watchnext.adapters.Card;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:14
 */
public class TvFragmentMvpContract {
    interface View {
        void showToast(String s);

        void onSucceedLoadingShowList(ListType listType);

        void onErrorLoadingShowList(ListType listType);

        void startTvDetailActivity(int tv_id);

        void notifyAdaptersNewData(ListType listType);

    }

    public interface Presenter {
        void onCreate();

        void onDestroy();

        //adapter calls
        void onBindCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

        int getCardsCount(ListType listType);

        void loadMore(ListType listType, int pageToLoad);
    }

    interface Model {

    }

}
