
package com.sagar.watchnext.network.models.movies.similars;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similars {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Similar> similars;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public List<Similar> getSimilars() {
        return similars;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
