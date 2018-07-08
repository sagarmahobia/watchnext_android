package com.sagar.watchnext.screens.moviedetailactivity;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.MovieList;
import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.movies.videos.Video;
import com.sagar.watchnext.network.models.movies.videos.Videos;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:19
 */

@MovieDetailActivityScope
public class Model implements MovieDetailActivityMvpContract.Model {

    private TmdbMovieRepo movieRepo;

    @Inject
    public Model(TmdbMovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public MovieDetail getMovieDetail(int movieId) throws IOException {
        return movieRepo.getMovieDetail(movieId).execute().body();
    }

    @Override
    public List<Movie> getRecommendations(int movieId) throws IOException {
        MovieList body = movieRepo.getRecommendations(movieId).execute().body();
        if (body != null) {
            return body.getMovies();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movie> getSimilars(int movieId) throws IOException {
        MovieList body = movieRepo.getSimilars(movieId).execute().body();
        if (body != null) {
            return body.getMovies();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Video> getVideos(int movieId) throws IOException {
        Videos body = movieRepo.getVideos(movieId).execute().body();
        if (body != null) {
            return body.getVideos();
        }
        return new ArrayList<>();
    }

}
