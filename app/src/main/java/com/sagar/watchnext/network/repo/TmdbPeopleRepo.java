package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.people.Persons;
import com.sagar.watchnext.network.models.people.detail.Detail;
import com.sagar.watchnext.network.models.people.images.Images;
import com.sagar.watchnext.network.models.people.moviecredits.MovieCredits;
import com.sagar.watchnext.network.models.people.tvcredits.TvCredits;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 19:07
 */
public interface TmdbPeopleRepo {
    @GET("person/popular")
    Observable<Persons> getPopularPeople();

    @GET("person/{person_id}")
    Observable<Detail> getDetail(@Path("person_id") int personId);

    @GET("person/{person_id}/images")
    Observable<Images> getImages(@Path("person_id") int personId);

    @GET("person/{person_id}/movie_credits")
    Observable<MovieCredits> getMovieCredits(@Path("person_id") int personId);

    @GET("person/{person_id}/tv_credits")
    Observable<TvCredits> getTvCredits(@Path("person_id") int personId);


}
