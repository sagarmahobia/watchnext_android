package com.sagar.watchnext.network.models.people.images;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 18:45
 */
public class Image {
    @SerializedName("aspect_ratio")
    private float aspectRatio;

    @SerializedName("file_path")
    private String filePath;

    @SerializedName("height")
    private int height;

    @SerializedName("iso_639_1")
    private Object iso6391;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("width")
    private int width;

    public float getAspectRatio() {
        return aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getHeight() {
        return height;
    }

    public Object getIso6391() {
        return iso6391;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getWidth() {
        return width;
    }
}
