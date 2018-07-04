package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.MovieList;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:06
 */
public interface MoviesFragmentMvpContract {
    interface View {
        void showToast(String msg);

        //In theater
        void onSucceedLoadingInTheatersMovieList();

        void onErrorLoadingInTheatersMovieList();


        //Upcoming
        void onSucceedLoadingUpcomingMovieList();

        void onErrorLoadingUpcomingMovieList();

        //popular
        void onSucceedLoadingPopularMovieList();

        void onErrorLoadingPopularMovieList();

        //top rated
        void onSucceedLoadingTopRatedMovieList();

        void onErrorLoadingTopRatedMovieList();
    }

    interface Presenter {
        //life cycle callbacks
        void onCreate();

        void onDestroy();

        //In theater
        void onBindInTheatersMovieCard(Card card, int position);

        void onInTheatersRecyclerItemClick(int position);

        int getInTheatersCardsCount();

        //upcoming
        void onBindUpcomingMovieCard(Card card, int position);

        void onUpcomingRecyclerItemClick(int position);

        int getUpComingCardsCount();

        //top rated
        void onBindPopularMovieCard(Card card, int position);

        void onPopularRecyclerItemClick(int position);

        int getPopularCardsCount();

        //top rated
        void onBindTopRatedMovieCard(Card  card , int position);

        void onTopRatedRecyclerItemClick(int position);

        int getTopRatedCardsCount();
    }

    interface Model {

        List<Movie> getInTheaterMovies() throws IOException;

        List<Movie> getUpcomingMovies() throws IOException;

        List<Movie> getPopularMovies() throws IOException;

        List<Movie> getTopRatedMovies() throws IOException;
    }
}
