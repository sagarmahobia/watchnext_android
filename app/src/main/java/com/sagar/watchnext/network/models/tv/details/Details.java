
package com.sagar.watchnext.network.models.tv.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Details {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("created_by")
    private List<CreatedBy> createdByList;

    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTimeList;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private int id;

    @SerializedName("in_production")
    private boolean inProduction;

    @SerializedName("languages")
    private List<String> languages;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("name")
    private String name;

    @SerializedName("networks")
    private List<Network> networks ;

    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("origin_country")
    private List<String> originCountries;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("seasons")
    private List<Season> seasons;

    @SerializedName("status")
    private String status;

    @SerializedName("type")
    private String type;

    @SerializedName("vote_average")
    private int voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

}
