
package com.sagar.watchnext.network.models.movies.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {

    @SerializedName("id")
    private int id;

    @SerializedName("backdrops")
    private List<Image> backdrops;

    @SerializedName("posters")
    private List<Image> posters;

    public int getId() {
        return id;
    }

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public List<Image> getPosters() {
        return posters;
    }
}