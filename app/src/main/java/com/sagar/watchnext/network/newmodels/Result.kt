package com.sagar.watchnext.network.newmodels

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("results")
    var cardItems: List<CardItem>? = null
        private set

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    fun setMovies(movies: List<CardItem>) {
        this.cardItems = movies
    }
}
