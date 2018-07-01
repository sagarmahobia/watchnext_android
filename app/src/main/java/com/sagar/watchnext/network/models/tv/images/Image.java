
package com.sagar.watchnext.network.models.tv.images;

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
    private int voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("width")
    private int width;

}
