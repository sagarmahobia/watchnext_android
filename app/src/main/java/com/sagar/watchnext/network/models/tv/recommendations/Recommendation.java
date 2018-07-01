
package com.sagar.watchnext.network.models.tv.recommendations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recommendation {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("id")
    private int id;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("overview")
    private String overview;

    @SerializedName("origin_country")
    private List<String> originCountries;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("name")
    private String name;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

}
