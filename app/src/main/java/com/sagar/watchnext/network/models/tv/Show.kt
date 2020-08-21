package com.sagar.watchnext.network.models.tv

import com.google.gson.annotations.SerializedName

class Show {

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("first_air_date")
    val firstAirDate: String? = null

    @SerializedName("origin_country")
    val originCountries: List<String>? = null

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("vote_count")
    val voteCount: Int = 0

    @SerializedName("name")
    val name: String? = null

    @SerializedName("original_name")
    val originalName: String? = null
}
