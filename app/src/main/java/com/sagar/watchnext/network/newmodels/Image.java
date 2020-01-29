
package com.sagar.watchnext.network.newmodels;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("aspect_ratio")
    private float aspectRatio;

    @SerializedName("file_path")
    private String filePath;

    @SerializedName("height")
    private int height;

    @SerializedName("iso_639_1")
    private String iso6391;

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

    public String getIso6391() {
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
