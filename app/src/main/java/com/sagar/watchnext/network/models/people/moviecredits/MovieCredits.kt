package com.sagar.watchnext.network.models.people.moviecredits

import com.google.gson.annotations.SerializedName

class MovieCredits {

    @SerializedName("cast")
    val casts: List<Cast>? = null

    @SerializedName("crew")
    val crews: List<Crew>? = null

    @SerializedName("id")
    val id: Int = 0
}
