package com.sagar.watchnext.network.models.people

import com.google.gson.annotations.SerializedName

class Person {

    @SerializedName("profile_path")
    val profilePath: String? = null

    @SerializedName("adult")
    val isAdult: Boolean = false

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("known_for")
    val knownFor: List<KnownFor>? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("popularity")
    val popularity: Float = 0.toFloat()

}
