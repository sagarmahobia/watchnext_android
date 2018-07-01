
package com.sagar.watchnext.network.models.tv.videos;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Video> videos ;

    public int getId() {
        return id;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
