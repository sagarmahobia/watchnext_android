package com.sagar.watchnext.network.models.people.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detail {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("also_known_as")
    private List<Object> alsoKnownAs = null;

    @SerializedName("biography")
    private String biography;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("deathday")
    private String deathDay;

    @SerializedName("gender")
    private int gender;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private int id;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("name")
    private String name;

    @SerializedName("place_of_birth")
    private String placeOfBirth;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("profile_path")
    private String profilePath;

    public boolean isAdult() {
        return adult;
    }

    public List<Object> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathDay() {
        return deathDay;
    }

    public int getGender() {
        return gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getName() {
        return name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }
}