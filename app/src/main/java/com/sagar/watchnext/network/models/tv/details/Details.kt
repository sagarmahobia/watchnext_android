package com.sagar.watchnext.network.models.tv.details

import com.google.gson.annotations.SerializedName

class Details {

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("created_by")
    val createdByList: List<CreatedBy>? = null

    @SerializedName("episode_run_time")
    val episodeRunTimeList: List<Int>? = null

    @SerializedName("first_air_date")
    val firstAirDate: String? = null

    @SerializedName("genres")
    val genres: List<Genre>? = null

    @SerializedName("homepage")
    val homepage: String? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("in_production")
    val isInProduction: Boolean = false

    @SerializedName("languages")
    val languages: List<String>? = null

    @SerializedName("last_air_date")
    val lastAirDate: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("networks")
    val networks: List<Network>? = null

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int = 0

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int = 0

    @SerializedName("origin_country")
    val originCountries: List<String>? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("original_name")
    val originalName: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null

    @SerializedName("seasons")
    val seasons: List<Season>? = null

    @SerializedName("status")
    val status: String? = null

    @SerializedName("type")
    val type: String? = null

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("vote_count")
    val voteCount: Int = 0
}
