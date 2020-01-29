package com.sagar.watchnext.network.models.people.moviecredits

import com.google.gson.annotations.SerializedName

class Crew {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("department")
    val department: String? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("job")
    val job: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("vote_count")
    val voteCount: Float = 0.toFloat()

    @SerializedName("video")
    val isVideo: Boolean = false

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("credit_id")
    val creditId: String? = null
}
