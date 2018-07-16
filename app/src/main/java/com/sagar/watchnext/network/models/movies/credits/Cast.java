
package com.sagar.watchnext.network.models.movies.credits;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("cast_id")
    private int castId;

    @SerializedName("character")
    private String character;

    @SerializedName("credit_id")
    private String creditId;

    @SerializedName("gender")
    private int gender;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("order")
    private int order;

    @SerializedName("profile_path")
    private Object profilePath;

    public int getCastId() {
        return castId;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public Object getProfilePath() {
        return profilePath;
    }
}
