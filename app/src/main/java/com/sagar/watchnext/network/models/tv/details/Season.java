
package com.sagar.watchnext.network.models.tv.details;

import com.google.gson.annotations.SerializedName;

public class Season {

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("episode_count")
    private int episodeCount;

    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("season_number")
    private int seasonNumber;

}
