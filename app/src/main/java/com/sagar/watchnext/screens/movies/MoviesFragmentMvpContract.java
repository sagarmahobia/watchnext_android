package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.MovieList;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:06
 */
interface MoviesFragmentMvpContract {
    interface View {
    }

    interface Presenter {
    }

    interface Model {

        List<Movie> getNowPlayingMovies() throws IOException;

        List<Movie> getUpcomingMovies() throws IOException;

        List<Movie> getPopularMovies() throws IOException;

        List<Movie> getTopRatedMovies() throws IOException;
    }
}
