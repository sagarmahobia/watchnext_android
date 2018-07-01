package com.sagar.watchnext.network.repo;

import com.sagar.watchnext.network.models.people.Persons;
import com.sagar.watchnext.network.models.people.detail.Detail;
import com.sagar.watchnext.network.models.people.images.Images;
import com.sagar.watchnext.network.models.people.moviecredits.MovieCredits;
import com.sagar.watchnext.network.models.people.tvcredits.TvCredits;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 19:07
 */
public interface TmdbPeopleRepo {
    @GET("person/popular")
    Call<Persons> getPopularPeople();

    @GET("person/{person_id}")
    Call<Detail> getDetail(@Path("person_id") int personId);

    @GET("person/{person_id}/images")
    Call<Images> getImages(@Path("person_id") int personId);

    @GET("person/{person_id}/movie_credits")
    Call<MovieCredits> getMovieCredits(@Path("person_id") int personId);

    @GET("person/{person_id}/tv_credits")
    Call<TvCredits> getTvCredits(@Path("person_id") int personId);


}
