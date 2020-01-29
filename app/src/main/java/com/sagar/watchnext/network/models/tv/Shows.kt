package com.sagar.watchnext.network.models.tv

import com.google.gson.annotations.SerializedName

class Shows {

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("results")
    val shows: List<Show>? = null

    @SerializedName("total_results")
    val totalResults: Int = 0

    @SerializedName("total_pages")
    val totalPages: Int = 0
}
