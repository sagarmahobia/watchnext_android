package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.MovieList;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:06
 */
@MoviesFragmentScope
public class Model implements MoviesFragmentMvpContract.Model {

    private TmdbMovieRepo tmdbMovieRepo;

    @Inject
    public Model(TmdbMovieRepo tmdbMovieRepo) {
        this.tmdbMovieRepo = tmdbMovieRepo;
    }

    @Override
    public List<Movie> getInTheaterMovies() throws IOException {
        MovieList movieList = tmdbMovieRepo.getInTheaterMovies().execute().body();

        if (movieList != null) {
            return movieList.getMovies();
        }
        return null;
    }

    @Override
    public List<Movie> getUpcomingMovies() throws IOException {
        MovieList movieList = tmdbMovieRepo.getUpcomingMovies().execute().body();

        if (movieList != null) {
            return movieList.getMovies();
        }
        return null;
    }

    @Override
    public List<Movie> getPopularMovies() throws IOException {
        MovieList movieList = tmdbMovieRepo.getPopularMovies().execute().body();

        if (movieList != null) {
            return movieList.getMovies();
        }
        return null;
    }

    @Override
    public List<Movie> getTopRatedMovies() throws IOException {
        MovieList movieList = tmdbMovieRepo.getTopRatedMovies().execute().body();

        if (movieList != null) {
            return movieList.getMovies();
        }
        return null;
    }

}
