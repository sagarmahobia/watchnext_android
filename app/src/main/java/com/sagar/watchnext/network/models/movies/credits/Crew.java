
package com.sagar.watchnext.network.models.movies.credits;

import com.google.gson.annotations.SerializedName;

public class Crew {

    @SerializedName("credit_id")
    private String creditId;

    @SerializedName("department")
    private String department;

    @SerializedName("gender")
    private int gender;

    @SerializedName("id")
    private int id;

    @SerializedName("job")
    private String job;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    public String getCreditId() {
        return creditId;
    }

    public String getDepartment() {
        return department;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
