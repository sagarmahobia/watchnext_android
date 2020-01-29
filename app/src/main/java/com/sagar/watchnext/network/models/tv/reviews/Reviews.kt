package com.sagar.watchnext.network.models.tv.reviews

import com.google.gson.annotations.SerializedName

class Reviews {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("results")
    val reviews: List<Review>? = null

    @SerializedName("total_pages")
    val totalPages: Int = 0

    @SerializedName("total_results")
    val totalResults: Int = 0
}
