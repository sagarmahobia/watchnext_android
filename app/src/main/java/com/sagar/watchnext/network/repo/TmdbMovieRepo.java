package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.movies.MovieList;
import com.sagar.watchnext.network.models.movies.credits.Credits;
import com.sagar.watchnext.network.models.movies.images.Images;
import com.sagar.watchnext.network.models.movies.lists.ListOfBelonging;
import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.movies.reviews.Reviews;
import com.sagar.watchnext.network.models.movies.videos.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 12:17
 */
public interface TmdbMovieRepo {

    //Lists
    @GET("movie/now_playing")
    Call<MovieList> getInTheaterMovies();

    @GET("movie/upcoming")
    Call<MovieList> getUpcomingMovies();

    @GET("movie/popular")
    Call<MovieList> getPopularMovies();

    @GET("movie/top_rated")
    Call<MovieList> getTopRatedMovies();

    //Lists
    @GET("movie/now_playing")
    Call<MovieList> getInTheaterMovies(@Query("page") int page);

    @GET("movie/upcoming")
    Call<MovieList> getUpcomingMovies(@Query("page") int page);

    @GET("movie/popular")
    Call<MovieList> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("page") int page);


    //per movie
    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/credits")
    Call<Credits> getCredits(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/images")
    Call<Images> getImages(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/videos")
    Call<Videos> getVideos(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/lists")
    Call<ListOfBelonging> getListOfBelonging(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/recommendations")
    Call<MovieList> getRecommendations(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> getReviews(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/similar")
    Call<MovieList> getSimilars(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/lists")
    Call<ListOfBelonging> getListOfBelonging(@Path("movie_id") int movieId, @Query("page") int page);

    @GET("movie/{movie_id}/recommendations")
    Call<MovieList> getRecommendations(@Path("movie_id") int movieId, @Query("page") int page);

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> getReviews(@Path("movie_id") int movieId, @Query("page") int page);

    @GET("movie/{movie_id}/similar")
    Call<MovieList> getSimilars(@Path("movie_id") int movieId, @Query("page") int page);


}
