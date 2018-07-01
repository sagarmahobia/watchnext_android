
package com.sagar.watchnext.network.models.people;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("profile_path")
    private String profilePath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("id")
    private int id;

    @SerializedName("known_for")
    private List<KnownFor> knownFor;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private float popularity;

    public String getProfilePath() {
        return profilePath;
    }


    public boolean isAdult() {
        return adult;
    }


    public int getId() {
        return id;
    }


    public List<KnownFor> getKnownFor() {
        return knownFor;
    }


    public String getName() {
        return name;
    }


    public float getPopularity() {
        return popularity;
    }

}
