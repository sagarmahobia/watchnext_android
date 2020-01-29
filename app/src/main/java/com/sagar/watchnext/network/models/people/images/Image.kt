package com.sagar.watchnext.network.models.people.images

import com.google.gson.annotations.SerializedName

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 18:45
 */
class Image {
    @SerializedName("aspect_ratio")
    val aspectRatio: Float = 0.toFloat()

    @SerializedName("file_path")
    val filePath: String? = null

    @SerializedName("height")
    val height: Int = 0

    @SerializedName("iso_639_1")
    val iso6391: Any? = null

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("vote_count")
    val voteCount: Int = 0

    @SerializedName("width")
    val width: Int = 0
}
