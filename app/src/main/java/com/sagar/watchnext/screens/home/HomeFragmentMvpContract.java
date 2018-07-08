package com.sagar.watchnext.screens.home;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.adapters.Card;

import java.io.IOException;
import java.util.List;

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
        //movie
        List<Movie> getInTheaterMovies() throws IOException;

        //tv
        List<Show> getOnTheAirTv() throws IOException;

    }
}
