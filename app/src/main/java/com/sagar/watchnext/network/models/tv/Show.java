
package com.sagar.watchnext.network.models.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Show {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private int voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("origin_country")
    private List<String> originCountries;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("name")
    private String name;

    @SerializedName("original_name")
    private String originalName;

}
