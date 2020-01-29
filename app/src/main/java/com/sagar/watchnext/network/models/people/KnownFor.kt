package com.sagar.watchnext.network.models.people

import com.google.gson.annotations.SerializedName

class KnownFor {

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("media_type")
    val mediaType: String? = null

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

    @SerializedName("first_air_date")
    val firstAirDate: String? = null

    @SerializedName("origin_country")
    val originCountry: List<String>? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("original_name")
    val originalName: String? = null
}
