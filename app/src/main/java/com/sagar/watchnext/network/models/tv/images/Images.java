
package com.sagar.watchnext.network.models.tv.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {

    @SerializedName("backdrops")
    private List<Image> backdrops;

    @SerializedName("id")
    private int id;

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
