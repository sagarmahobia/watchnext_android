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
    private int voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("width")
    private int width;

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Object getIso6391() {
        return iso6391;
    }

    public void setIso6391(Object iso6391) {
        this.iso6391 = iso6391;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
