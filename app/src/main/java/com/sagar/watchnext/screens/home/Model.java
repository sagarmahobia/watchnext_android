package com.sagar.watchnext.screens.home;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.MovieList;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.models.tv.Shows;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:10
 */
@HomeFragmentScope
public class Model implements HomeFragmentMvpContract.Model {

    private TmdbTvRepo tvRepo;

    private TmdbMovieRepo movieRepo;

    @Inject
    public Model(TmdbTvRepo tvRepo, TmdbMovieRepo movieRepo) {
        this.tvRepo = tvRepo;
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Movie> getInTheaterMovies() throws IOException {
        MovieList movieList = movieRepo.getInTheaterMovies().execute().body();

        if (movieList != null) {
            return movieList.getMovies();
        }
        return null;
    }

    @Override
    public List<Show> getOnTheAirTv() throws IOException {
        Shows shows = tvRepo.getOnTheAir().execute().body();
        if (shows != null) {
            return shows.getShows();
        }
        return null;
    }
}
