
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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(Object posterPath) {
        this.posterPath = posterPath;
    }
}
