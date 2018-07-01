package com.sagar.watchnext.network.models.people.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 18:44
 */
public class Images {
    @SerializedName("id")
    private int id;

    @SerializedName("profiles")
    private List<Images> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
