
package com.sagar.watchnext.network.models.movies.moviedetail;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {

    @SerializedName("iso_639_1")
    private String iso6391;

    @SerializedName("name")
    private String name;

    public String getIso6391() {
        return iso6391;
    }

    public String getName() {
        return name;
    }
}
