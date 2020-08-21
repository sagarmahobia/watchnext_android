package com.sagar.watchnext.network.models.people.moviecredits

import com.google.gson.annotations.SerializedName

class Cast {

    @SerializedName("character")
    val character: String? = null

    @SerializedName("credit_id")
    val creditId: String? = null

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("vote_count")
    val voteCount: Int = 0

    @SerializedName("video")
    val isVideo: Boolean = false

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("title")
    val title: String? = null

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null
}
