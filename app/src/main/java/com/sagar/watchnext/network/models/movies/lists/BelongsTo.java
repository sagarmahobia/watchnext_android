
package com.sagar.watchnext.network.models.movies.lists;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;

public class BelongsTo {

    @SerializedName("description")
    private String description;

    @SerializedName("favorite_count")
    private int favoriteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("item_count")
    private int itemCount;

    @SerializedName("iso_639_1")
    private String iso6391;

    @SerializedName("list_type")
    private String listType;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private Object posterPath;

    public String getDescription() {
        return description;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public int getId() {
        return id;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getIso6391() {
        return iso6391;
    }

    public String getListType() {
        return listType;
    }

    public String getName() {
        return name;
    }

    public Object getPosterPath() {
        return posterPath;
    }
}
