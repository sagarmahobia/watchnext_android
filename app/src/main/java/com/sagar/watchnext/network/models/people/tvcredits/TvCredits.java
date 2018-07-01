
package com.sagar.watchnext.network.models.people.tvcredits;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvCredits {

    @SerializedName("cast")
    private List<Cast> cast;

    @SerializedName("crew")
    private List<Crew> crew;

    @SerializedName("id")
    private int id;

    public List<Cast> getCast() {
        return cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public int getId() {
        return id;
    }
}
