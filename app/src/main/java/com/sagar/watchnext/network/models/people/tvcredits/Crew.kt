package com.sagar.watchnext.network.models.people.tvcredits

import com.google.gson.annotations.SerializedName

class Crew {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("department")
    val department: String? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("episode_count")
    val episodeCount: Int = 0

    @SerializedName("job")
    val job: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("origin_country")
    val originCountry: List<String>? = null


    @SerializedName("original_name")
    val originalName: String? = null

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("first_air_date")
    val firstAirDate: String? = null

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("vote_count")
    val voteCount: Int = 0

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("credit_id")
    val creditId: String? = null
}
