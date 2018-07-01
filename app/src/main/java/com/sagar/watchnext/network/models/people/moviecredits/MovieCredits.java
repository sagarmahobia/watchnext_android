
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

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
