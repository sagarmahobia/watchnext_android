
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

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BelongsTo> getListBelongsTo() {
        return listBelongsTo;
    }

    public void setListBelongsTo(List<BelongsTo> listBelongsTo) {
        this.listBelongsTo = listBelongsTo;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
