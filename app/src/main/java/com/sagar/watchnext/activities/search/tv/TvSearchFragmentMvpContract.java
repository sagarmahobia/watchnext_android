package com.sagar.watchnext.activities.search.tv;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.adapters.search.SearchCard;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 14:05
 */
public interface TvSearchFragmentMvpContract {
    interface View {
        void showToast(String msg);

        void onErrorLoadingShowList();

        void notifyAdapter();

        void resetEndlessLoader();

        void startTvDetailActivity(int tv_id);

        void showProgress();

        void hideProgress();

    }

    interface Model {
    }

    interface Presenter {
        void onCreate();

        void onDestroy();

        void query(String query);

        int getCardsCount();

        void onBindCard(SearchCard card, int position);

        void onRecyclerItemClick(int position);

        void onLoadMore(int pageToLoad);
    }

}
