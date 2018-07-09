package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.movies.MovieList;
import com.sagar.watchnext.network.models.movies.credits.Credits;
import com.sagar.watchnext.network.models.movies.images.Images;
import com.sagar.watchnext.network.models.movies.lists.ListOfBelonging;
import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.movies.reviews.Reviews;
import com.sagar.watchnext.network.models.movies.videos.Videos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 12:17
 */
public interface TmdbMovieRepo {

    //Lists
    @GET("movie/now_playing")
    Observable<MovieList> getInTheaterMovies();

    @GET("movie/upcoming")
    Observable<MovieList> getUpcomingMovies();

    @GET("movie/popular")
    Observable<MovieList> getPopularMovies();

    @GET("movie/top_rated")
    Observable<MovieList> getTopRatedMovies();

    //Lists with page id
    @GET("movie/now_playing")
    Observable<MovieList> getInTheaterMovies(@Query("page") int page);

    @GET("movie/upcoming")
    Observable<MovieList> getUpcomingMovies(@Query("page") int page);

    @GET("movie/popular")
    Observable<MovieList> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated")
    Observable<MovieList> getTopRatedMovies(@Query("page") int page);


    //per movie
    @GET("movie/{movie_id}")
    Observable<MovieDetail> getMovieDetail(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/credits")
    Observable<Credits> getCredits(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/images")
    Observable<Images> getImages(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/videos")
    Observable<Videos> getVideos(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/lists")
    Observable<ListOfBelonging> getListOfBelonging(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/recommendations")
    Observable<MovieList> getRecommendations(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/reviews")
    Observable<Reviews> getReviews(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/similar")
    Observable<MovieList> getSimilars(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/lists")
    Observable<ListOfBelonging> getListOfBelonging(@Path("movie_id") int movieId, @Query("page") int page);

    @GET("movie/{movie_id}/recommendations")
    Observable<MovieList> getRecommendations(@Path("movie_id") int movieId, @Query("page") int page);

    @GET("movie/{movie_id}/reviews")
    Observable<Reviews> getReviews(@Path("movie_id") int movieId, @Query("page") int page);

    @GET("movie/{movie_id}/similar")
    Observable<MovieList> getSimilars(@Path("movie_id") int movieId, @Query("page") int page);


}
