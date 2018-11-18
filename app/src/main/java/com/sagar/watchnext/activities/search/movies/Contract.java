package com.sagar.watchnext.activities.search.movies;

import android.arch.lifecycle.LifecycleObserver;

import com.sagar.watchnext.adapters.search.SearchCard;

/**
 * Created by SAGAR MAHOBIA on 14-Jul-18. at 00:22
 */
public interface Contract {


    interface View {
        void showToast(String msg);

        void onErrorLoadingShowList();

        void notifyAdapter();

        void resetEndlessLoader();

        void startMovieDetailActivity(int movieId);

        void showProgress();

        void hideProgress();

        void hideErrorMessage();

        void showErrorMessage();

        void showNoMatchMessage();
    }

    interface Presenter extends LifecycleObserver {

        void query(String query);

        int getCardsCount();

        void onBindCard(SearchCard card, int position);

        void onRecyclerItemClick(int position);

        void onLoadMore(int pageToLoad);
    }

}

