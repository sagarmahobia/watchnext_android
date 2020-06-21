package com.sagar.watchnext.network.models.movies.moviedetail

import com.google.gson.annotations.SerializedName

class MovieDetail {

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("backdrop_path")
    val backdropPath: String? = null
    //todo check data type

    /* "belongs_to_collection": {
    "id": 344830,
    "name": "Fifty Shades Collection",
    "poster_path": "/oJrMaAhQlV5K9QFhulFehTn7JVn.jpg",
    "backdrop_path": "/5OmblvyjPX4QRI77y9LPGttbHct.jpg"

  }*/

    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null

    @SerializedName("budget")
    val budget: Int = 0

    @SerializedName("genres")
    val genres: List<Genre>? = null

    @SerializedName("homepage")
    val homepage: String? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("imdb_id")
    val imdbId: String? = null

    @SerializedName("original_language")
    val originalLanguage: String? = null

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("revenue")
    val revenue: Int = 0

    @SerializedName("runtime")
    val runtime: Int = 0

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null

    @SerializedName("status")
    val status: String? = null

    @SerializedName("tagline")
    val tagline: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("video")
    val isVideo: Boolean = false

    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat()

    @SerializedName("vote_count")
    val voteCount: Int = 0
}
