package com.sagar.watchnext.network.models.tv.videos

import com.google.gson.annotations.SerializedName

class Video {

    @SerializedName("id")
    val id: String? = null

    @SerializedName("iso_639_1")
    val iso6391: String? = null

    @SerializedName("iso_3166_1")
    val iso31661: String? = null

    @SerializedName("key")
    val key: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("site")
    val site: String? = null

    @SerializedName("size")
    val size: Int = 0

    @SerializedName("type")
    val type: String? = null
}
