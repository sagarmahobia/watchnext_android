package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.adapters.Card;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:06
 */
public interface MoviesFragmentMvpContract {
    interface View {
        void showToast(String msg);

        //combined
        void onSucceedLoadingMovieList(ListType listType);

        void onErrorLoadingMovieList(ListType listType);

        void startMovieDetailActivity(int movieId);
    }

    interface Presenter {
        //life cycle callbacks
        void onCreate();

        void onDestroy();

        //combined
        void onBindMovieCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

        int getCardsCount(ListType listType);


    }

    interface Model {

    }
}
