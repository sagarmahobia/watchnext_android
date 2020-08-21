package com.sagar.watchnext.network.models.movies.lists

import com.google.gson.annotations.SerializedName

class ListOfBelonging {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("results")
    val listBelongsTo: List<BelongsTo>? = null

    @SerializedName("total_pages")
    val totalPages: Int = 0

    @SerializedName("total_results")
    val totalResults: Int = 0
}
