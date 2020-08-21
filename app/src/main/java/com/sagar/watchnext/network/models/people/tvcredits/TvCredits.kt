package com.sagar.watchnext.network.models.people.tvcredits

import com.google.gson.annotations.SerializedName

class TvCredits {

    @SerializedName("cast")
    val cast: List<Cast>? = null

    @SerializedName("crew")
    val crew: List<Crew>? = null

    @SerializedName("id")
    val id: Int = 0
}
