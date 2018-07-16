
package com.sagar.watchnext.network.models.people.moviecredits;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crew {

    @SerializedName("id")
    private int id;

    @SerializedName("department")
    private String department;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("job")
    private String job;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_count")
    private float voteCount;

    @SerializedName("video")
    private boolean video;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("genre_ids")
    private List<Integer> genreIds = null;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("release_date")
    private String releaseDate;

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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getJob() {
        return job;
    }

    public String getOverview() {
        return overview;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public float getPopularity() {
        return popularity;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getCreditId() {
        return creditId;
    }
}
