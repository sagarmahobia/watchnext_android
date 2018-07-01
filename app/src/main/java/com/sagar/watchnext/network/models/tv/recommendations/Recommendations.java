
package com.sagar.watchnext.network.models.tv.recommendations;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Recommendations {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Recommendation> recommendations;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

}
