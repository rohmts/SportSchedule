package com.rohmats.kotlinsport.api

import com.rohmats.kotlinsport.BuildConfig

/**
 * Created by Rohmats on 21/10/18.
 */

object TheSportDBApi {
    fun getNextEvent(leagueId: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/eventsnextleague.php?id=" + leagueId
    }

    fun getLastEvent(leagueId: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/eventspastleague.php?id=" + leagueId
    }

    fun getDetailEvent(eventId: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/lookupevent.php?id=" + eventId
    }

    fun getDetailTeam(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/lookupteam.php?id=" + teamId
    }
}