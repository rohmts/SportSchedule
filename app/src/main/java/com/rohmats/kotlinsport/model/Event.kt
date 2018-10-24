package com.rohmats.kotlinsport.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rohmats on 21/10/18.
 */

data class Event(
    @SerializedName("idEvent") var eventId: String? = null,
    @SerializedName("idHomeTeam") var eventHomeTeamId: String? = null,
    @SerializedName("idAwayTeam") var eventAwayTeamId: String? = null,
    @SerializedName("strHomeTeam") var eventHomeTeamName: String? = null,
    @SerializedName("strAwayTeam") var eventAwayTeamName: String? = null,
    @SerializedName("intHomeScore") var eventHomeScore: String? = null,
    @SerializedName("intAwayScore") var eventAwayScore: String? = null,
    @SerializedName("strDate") var eventDate: String? = null,
    @SerializedName("strTime") var eventTime: String? = null,
    @SerializedName("intHomeShots") var eventHomeShots: String? = null,
    @SerializedName("intAwayShots") var eventAwayShots: String? = null,
    @SerializedName("strHomeGoalDetails") var eventHomeGoalDetails: String? = null,
    @SerializedName("strAwayGoalDetails") var eventAwayGoalDetails: String? = null,
    @SerializedName("strHomeLineupGoalkeeper") var eventHomeLineupGk: String? = null,
    @SerializedName("strHomeLineupDefense") var eventHomeLineupDefense: String? = null,
    @SerializedName("strHomeLineupMidfield") var eventHomeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupForward") var eventHomeLineupForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes") var eventHomeLineupSubs: String? = null,
    @SerializedName("strAwayLineupGoalkeeper") var eventAwayLineupGk: String? = null,
    @SerializedName("strAwayLineupDefense") var eventAwayLineupDefense: String? = null,
    @SerializedName("strAwayLineupMidfield") var eventAwayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupForward") var eventAwayLineupForward: String? = null,
    @SerializedName("strAwayLineupSubstitutes") var eventAwayLineupSubs: String? = null
)