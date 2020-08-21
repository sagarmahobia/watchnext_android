package com.sagar.watchnext.network.models.people.tvcredits

import com.google.gson.annotations.SerializedName

class Cast {

    @SerializedName("credit_id")
    val creditId: String? = null

    @SerializedName("original_name")
    val originalName: String? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("character")
    val character: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("vote_count")
    val voteCount: Int = 0

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("episode_count")
    val episodeCount: Int = 0

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("first_air_date")
    val firstAirDate: String? = null

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("origin_country")
    val originCountry: List<String>? = null
}
