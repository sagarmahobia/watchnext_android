
package com.sagar.watchnext.network.models.people.tvcredits;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cast {

    @SerializedName("credit_id")
    private String creditId;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("id")
    private int id;

    @SerializedName("genre_ids")
    private List<Integer> genreIds ;

    @SerializedName("character")
    private String character;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("episode_count")
    private int episodeCount;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("origin_country")
    private List<String> originCountry ;

    public String getCreditId() {
        return creditId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }
}
