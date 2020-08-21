package com.sagar.watchnext.network.newmodels

import com.google.gson.annotations.SerializedName

class CardItem {
    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("adult")
    var isAdult: Boolean = false

    @SerializedName("id")
    var id: Int = 0

    @SerializedName(value = "title", alternate = ["name"])
    var title: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName(value = "release_date", alternate = ["first_air_date"])
    var releaseOrFirstAirDate: String? = null
        private set

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null

    @SerializedName("popularity")
    var popularity: Float = 0.toFloat()

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("vote_average")
    var voteAverage: Float = 0.toFloat()

}
