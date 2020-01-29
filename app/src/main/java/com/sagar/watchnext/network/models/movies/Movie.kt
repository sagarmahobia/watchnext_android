package com.sagar.watchnext.network.models.movies

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("vote_count")
    val voteCount: Int = 0

    @SerializedName("video")
    val isVideo: Boolean = false

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()
}
