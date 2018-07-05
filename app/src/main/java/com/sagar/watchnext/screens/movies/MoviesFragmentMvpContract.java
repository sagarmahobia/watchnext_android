package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.movies.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:06
 */
public interface MoviesFragmentMvpContract {
    interface View {
        void showToast(String msg);

        //combined
        void onSucceedLoadingMovieList(ListType listType);

        void onErrorLoadingMovieList(ListType listType);
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

        List<Movie> getInTheaterMovies() throws IOException;

        List<Movie> getUpcomingMovies() throws IOException;

        List<Movie> getPopularMovies() throws IOException;

        List<Movie> getTopRatedMovies() throws IOException;
    }
}
