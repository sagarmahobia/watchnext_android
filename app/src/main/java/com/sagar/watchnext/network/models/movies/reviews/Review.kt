package com.sagar.watchnext.network.models.movies.reviews

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Review {

    @SerializedName("id")
    val id: String? = null

    @SerializedName("author")
    val author: String? = null

    @SerializedName("content")
    val content: String? = null

    @SerializedName("url")
    val url: String? = null
}
