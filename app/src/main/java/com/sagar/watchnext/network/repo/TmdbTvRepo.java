package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.tv.Shows;
import com.sagar.watchnext.network.models.tv.credits.Credits;
import com.sagar.watchnext.network.models.tv.details.Details;
import com.sagar.watchnext.network.models.tv.images.Images;
import com.sagar.watchnext.network.models.tv.reviews.Reviews;
import com.sagar.watchnext.network.models.tv.videos.Videos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA on 02-Jul-18. at 00:00
 */
public interface TmdbTvRepo {
    @GET("tv/top_rated")
    Observable<Shows> getTopRated();

    @GET("tv/popular")
    Observable<Shows> getPopular();

    @GET("tv/on_the_air")
    Observable<Shows> getOnTheAir();

    @GET("tv/airing_today")
    Observable<Shows> getAiringToday();

    @GET("tv/top_rated")
    Observable<Shows> getTopRated(@Query("page") int page);

    @GET("tv/popular")
    Observable<Shows> getPopular(@Query("page") int page);

    @GET("tv/on_the_air")
    Observable<Shows> getOnTheAir(@Query("page") int page);

    @GET("tv/airing_today")
    Observable<Shows> getAiringToday(@Query("page") int page);

    @GET("search/tv")
    Observable<Shows> searchByQuery(@Query("query") String query);

    @GET("search/tv")
    Observable<Shows> searchByQuery(@Query("query") String query, @Query("page") int page);

    @GET("tv/{tv_id}")
    Observable<Details> getDetails(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/credits")
    Observable<Credits> getCredits(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/images")
    Observable<Images> getImages(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/videos")
    Observable<Videos> getVideos(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/similar")
    Observable<Shows> getSimilars(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/reviews")
    Observable<Reviews> getReviews(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/recommendations")
    Observable<Shows> getRecommendations(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/similar")
    Observable<Shows> getSimilars(@Path("tv_id") int tvId, @Query("page") int page);

    @GET("tv/{tv_id}/reviews")
    Observable<Reviews> getReviews(@Path("tv_id") int tvId, @Query("page") int page);

    @GET("tv/{tv_id}/recommendations")
    Observable<Shows> getRecommendations(@Path("tv_id") int tvId, @Query("page") int page);

}
