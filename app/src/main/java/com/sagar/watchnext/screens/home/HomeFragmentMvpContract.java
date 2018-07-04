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

        void onSucceedLoadingMovieList();

        void onErrorLoadingMovieList();

        void onSucceedLoadingTvList();

        void onErrorLoadingTvList();

    }

    interface Presenter {
        //life cycle callback
        void onCreate();

        void onDestroy();


        //movie

        int getMovieCardsCount();

        void onBindMovieCard(Card card, int position);

        void onMovieRecyclerItemClick(int position);


        //tv

        int getTvCardsCount();

        void onBindTvCard(Card card, int position);

        void onTvRecyclerItemClick(int position);

    }

    interface Model {

        List<Movie> getNowPlayingMovies() throws IOException;

        List<Show> getOnTheAirTv() throws IOException;

    }
}
