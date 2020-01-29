package com.sagar.watchnext.network.models.movies

import com.google.gson.annotations.SerializedName

class MovieList {

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("results")
    val movies: List<Movie>? = null

    @SerializedName("dates")
    val dates: Dates? = null

    @SerializedName("total_pages")
    val totalPages: Int = 0

    @SerializedName("total_results")
    val totalResults: Int = 0
}
