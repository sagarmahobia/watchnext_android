package com.sagar.watchnext.network.newmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardItem {
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("id")
    private int id;

    @SerializedName(value = "title", alternate = "name")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName(value = "release_date", alternate = "first_air_date")
    private String releaseDate;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("vote_average")
    private float voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseOrFirstAirDate() {
        return releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
}
