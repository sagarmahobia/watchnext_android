
package com.sagar.watchnext.network.models.tv.reviews;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Reviews {

    @SerializedName("id")
        private int id;

    @SerializedName("page")
        private int page;

    @SerializedName("results")
        private List<Review> reviews ;

    @SerializedName("total_pages")
        private int totalPages;

    @SerializedName("total_results")
        private int totalResults;

}
