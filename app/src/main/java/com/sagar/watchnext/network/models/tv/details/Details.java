
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
    private float voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<CreatedBy> getCreatedByList() {
        return createdByList;
    }

    public List<Integer> getEpisodeRunTimeList() {
        return episodeRunTimeList;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public String getName() {
        return name;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public List<String> getOriginCountries() {
        return originCountries;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
