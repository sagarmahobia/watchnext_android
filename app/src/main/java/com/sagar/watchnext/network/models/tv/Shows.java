
package com.sagar.watchnext.network.models.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shows {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Show> shows;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

}
