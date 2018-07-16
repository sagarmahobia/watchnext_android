
package com.sagar.watchnext.network.models.movies.lists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfBelonging {

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<BelongsTo> listBelongsTo;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<BelongsTo> getListBelongsTo() {
        return listBelongsTo;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
