package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.movies.credits.Credits;
import com.sagar.watchnext.network.models.movies.lists.ListOfBelonging;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 12:17
 */
public interface TmdbMovieRepo {


    @GET("movie/{movie_id}/credits")
    Observable<Credits> getCredits(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/lists")
    Observable<ListOfBelonging> getListOfBelonging(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/lists")
    Observable<ListOfBelonging> getListOfBelonging(@Path("movie_id") int movieId, @Query("page") int page);

}
