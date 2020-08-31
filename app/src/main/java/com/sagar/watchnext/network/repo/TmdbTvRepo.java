package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.tv.credits.Credits;
import com.sagar.watchnext.network.newmodels.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA on 02-Jul-18. at 00:00
 */

public interface TmdbTvRepo {

    @GET("tv/{tv_id}/credits")
    Observable<Credits> getCredits(@Path("tv_id") int tvId);

    @GET("discover/tv?sort_by=popularity.desc&with_networks=213")
    Observable<Result> getNetflixShows(@Query("page") int page);

    @GET("discover/tv?sort_by=popularity.desc&with_networks=1024")
    Observable<Result> getAmazonShows(@Query("page") int page);

    @GET("discover/tv?sort_by=popularity.desc&with_networks=2552")
    Observable<Result> getAppleTVShows(@Query("page") int page);

    @GET("discover/tv?sort_by=popularity.desc&with_networks=2739")
    Observable<Result> getDisneyPlusShows(@Query("page") int page);

}
