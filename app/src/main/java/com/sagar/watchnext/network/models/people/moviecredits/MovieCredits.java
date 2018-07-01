
package com.sagar.watchnext.network.models.people.moviecredits;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCredits {

    @SerializedName("cast")
    private List<Cast> casts;

    @SerializedName("crew")
    private List<Crew> crews;

    @SerializedName("id")
    private int id;

    public List<Cast> getCasts() {
        return casts;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public int getId() {
        return id;
    }
}
