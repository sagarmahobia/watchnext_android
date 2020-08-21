package com.sagar.watchnext.network.models.tv.videos

import com.google.gson.annotations.SerializedName

class Videos {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("results")
    val videos: List<Video>? = ArrayList()
}
