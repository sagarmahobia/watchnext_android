package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.tv.details.Details;
import com.sagar.watchnext.network.models.tv.reviews.Reviews;
import com.sagar.watchnext.network.models.tv.videos.Videos;
import com.sagar.watchnext.network.newmodels.Images;
import com.sagar.watchnext.network.newmodels.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA
 */
public interface TMDBRepository {

    @GET("tv/{tv_id}")
    Observable<Details> getTVDetails(@Path("tv_id") int tvId);

    //per movie
    @GET("movie/{movie_id}")
    Observable<MovieDetail> getMovieDetails(@Path("movie_id") int movieId);

    /*
     *
     *
     *
     * */
    @GET("{type}/{subtype}")
    Observable<Result> getFirstPage(@Path("type") String type, @Path("subtype") String subtype);

    //Lists with page id
    @GET("{type}/{subtype}")
    Observable<Result> getPagedList(@Path("type") String type, @Path("subtype") String subtype, @Query("page") int page);

    @GET("search/{type}")
    Observable<Result> searchByQuery(@Path("type") String type, @Query("query") String query, @Query("page") int page);

    @GET("{type}/{id}/images")
    Observable<Images> getImages(@Path("type") String type, @Path("id") int id);

    /*
     *  @GET("{type}/{id}/recommendations")
     *  @GET("{type}/{id}/similar")
     *
     * */

    @GET("{type}/{id}/{subtype}")
    Observable<Result> getListWithId(@Path("type") String type, @Path("id") int id, @Path("subtype") String subtype, @Query("page") int page);

    @GET("{type}/{id}/videos")
    Observable<Videos> getVideos(@Path("type") String type, @Path("id") int tvId);

    @GET("{type}/{id}/reviews")
    Observable<Reviews> getReviews(@Path("type") String type, @Path("id") int id, @Query("page") int page);

}
