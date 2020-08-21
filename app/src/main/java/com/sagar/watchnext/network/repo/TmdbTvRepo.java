package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.tv.credits.Credits;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SAGAR MAHOBIA on 02-Jul-18. at 00:00
 */

public interface TmdbTvRepo {

    @GET("tv/{tv_id}/credits")
    Observable<Credits> getCredits(@Path("tv_id") int tvId);

}
