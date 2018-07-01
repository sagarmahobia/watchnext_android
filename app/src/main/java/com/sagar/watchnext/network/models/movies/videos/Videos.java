
package com.sagar.watchnext.network.models.movies.videos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Video> videos = null;

    public int getId() {
        return id;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
