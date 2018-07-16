
package com.sagar.watchnext.network.models.people.tvcredits;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crew {

    @SerializedName("id")
    private int id;

    @SerializedName("department")
    private String department;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("episode_count")
    private int episodeCount;

    @SerializedName("job")
    private String job;

    @SerializedName("overview")
    private String overview;

    @SerializedName("origin_country")
    private List<String> originCountry;


    @SerializedName("original_name")
    private String originalName;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("name")
    private String name;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("credit_id")
    private String creditId;

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public String getJob() {
        return job;
    }

    public String getOverview() {
        return overview;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public String getOriginalName() {
        return originalName;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getCreditId() {
        return creditId;
    }
}
