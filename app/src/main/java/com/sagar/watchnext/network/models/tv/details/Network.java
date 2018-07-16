
package com.sagar.watchnext.network.models.tv.details;

import com.google.gson.annotations.SerializedName;

public class Network {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
