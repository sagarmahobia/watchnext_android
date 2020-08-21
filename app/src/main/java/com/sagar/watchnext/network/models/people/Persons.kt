package com.sagar.watchnext.network.models.people

import com.google.gson.annotations.SerializedName

class Persons {

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("results")
    val persons: List<Person>? = null

    @SerializedName("total_results")
    val totalResults: Int = 0

    @SerializedName("total_pages")
    val totalPages: Int = 0

}
