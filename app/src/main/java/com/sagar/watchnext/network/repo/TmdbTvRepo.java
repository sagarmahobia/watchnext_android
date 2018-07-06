package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.tv.Shows;
import com.sagar.watchnext.network.models.tv.credits.Credits;
import com.sagar.watchnext.network.models.tv.details.Details;
import com.sagar.watchnext.network.models.tv.images.Images;
import com.sagar.watchnext.network.models.tv.reviews.Reviews;
import com.sagar.watchnext.network.models.tv.videos.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA on 02-Jul-18. at 00:00
 */
public interface TmdbTvRepo {
    @GET("tv/top_rated")
    Call<Shows> getTopRated();

    @GET("tv/popular")
    Call<Shows> getPopular();

    @GET("tv/on_the_air")
    Call<Shows> getOnTheAir();

    @GET("tv/airing_today")
    Call<Shows> getAiringToday();

    @GET("tv/top_rated")
    Call<Shows> getTopRated(@Query("page") int page);

    @GET("tv/popular")
    Call<Shows> getPopular(@Query("page") int page);

    @GET("tv/on_the_air")
    Call<Shows> getOnTheAir(@Query("page") int page);

    @GET("tv/airing_today")
    Call<Shows> getAiringToday(@Query("page") int page);

    @GET("tv/{tv_id}")
    Call<Details> getDetails(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/credits")
    Call<Credits> getCredits(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/images")
    Call<Images> getImages(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/videos")
    Call<Videos> getVideos(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/similar")
    Call<Shows> getSimilars(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/reviews")
    Call<Reviews> getReviews(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/recommendations")
    Call<Shows> getRecommendations(@Path("tv_id") int tvId);

    @GET("tv/{tv_id}/similar")
    Call<Shows> getSimilars(@Path("tv_id") int tvId, @Query("page") int page);

    @GET("tv/{tv_id}/reviews")
    Call<Reviews> getReviews(@Path("tv_id") int tvId, @Query("page") int page);

    @GET("tv/{tv_id}/recommendations")
    Call<Shows> getRecommendations(@Path("tv_id") int tvId, @Query("page") int page);

}
