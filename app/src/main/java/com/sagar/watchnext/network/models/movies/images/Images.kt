package com.sagar.watchnext.network.models.movies.images

import com.google.gson.annotations.SerializedName

class Images {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("backdrops")
    val backdrops: List<Image>? = null

    @SerializedName("posters")
    val posters: List<Image>? = null
}