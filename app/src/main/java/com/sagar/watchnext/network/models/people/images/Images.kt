package com.sagar.watchnext.network.models.people.images

import com.google.gson.annotations.SerializedName

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 18:44
 */
class Images {
    @SerializedName("id")
    val id: Int = 0

    @SerializedName("profiles")
    val images: List<Images>? = null
}
