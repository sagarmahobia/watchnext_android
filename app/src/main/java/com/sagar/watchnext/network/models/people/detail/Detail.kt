package com.sagar.watchnext.network.models.people.detail

import com.google.gson.annotations.SerializedName

class Detail {

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("also_known_as")
    val alsoKnownAs: List<Any>? = null

    @SerializedName("biography")
    val biography: String? = null

    @SerializedName("birthday")
    val birthday: String? = null

    @SerializedName("deathday")
    val deathDay: String? = null

    @SerializedName("gender")
    val gender: Int = 0

    @SerializedName("homepage")
    val homepage: String? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("imdb_id")
    val imdbId: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("place_of_birth")
    val placeOfBirth: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

    @SerializedName("profile_path")
    val profilePath: String? = null
}