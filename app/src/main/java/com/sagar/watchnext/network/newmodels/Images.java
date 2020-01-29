
package com.sagar.watchnext.network.newmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {

    @SerializedName("id")
    private int id;

    @SerializedName("backdrops")
    private List<Image> backdrops;

    @SerializedName("posters")
    private List<Image> posters;

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public int getId() {
        return id;
    }

    public List<Image> getPosters() {
        return posters;
    }
}
