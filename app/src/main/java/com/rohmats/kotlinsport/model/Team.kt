package com.rohmats.kotlinsport.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rohmats on 21/10/18.
 */

data class Team(
    @SerializedName("idTeam") var teamId: String? = null,
    @SerializedName("strTeam") var teamName: String? = null,
    @SerializedName("strTeamBadge") var teamBadge: String? = null
)