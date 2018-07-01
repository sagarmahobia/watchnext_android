
package com.sagar.watchnext.network.models.tv.details;

import com.google.gson.annotations.SerializedName;

public class CreatedBy {

    @SerializedName("id")
        private int id;

    @SerializedName("name")
        private String name;

    @SerializedName("gender")
        private int gender;

    @SerializedName("profile_path")
        private String profilePath;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
