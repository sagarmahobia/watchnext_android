
package com.sagar.watchnext.network.models.tv.credits;

import com.google.gson.annotations.SerializedName;

public class Cast {

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
    private String profilePath;

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

    public String getProfilePath() {
        return profilePath;
    }
}
