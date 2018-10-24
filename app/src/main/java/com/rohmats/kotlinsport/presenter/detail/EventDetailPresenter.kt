package com.rohmats.kotlinsport.presenter.detail

import com.google.gson.Gson
import com.rohmats.kotlinsport.api.ApiRepository
import com.rohmats.kotlinsport.api.TheSportDBApi
import com.rohmats.kotlinsport.model.EventResponse
import com.rohmats.kotlinsport.model.TeamResponse
import com.rohmats.kotlinsport.utilities.CoroutineContextProvider
import com.rohmats.kotlinsport.views.detail.DetailEventView
import com.rohmats.kotlinsport.views.home.EventView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Rohmats on 22/10/18.
 */

class EventDetailPresenter(private val view: DetailEventView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailEvent(leagueId: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailEvent(leagueId)), EventResponse::class.java)
            }
            view.showDetailEvent(data.await().events)
        }
    }

    fun getDetailHomeTeam(teamId: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(teamId)), TeamResponse::class.java)
            }
            view.showDetailHomeTeam(data.await().teams)
            view.hideLoading()
        }
    }

    fun getDetailAwayTeam(teamId: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(teamId)), TeamResponse::class.java)
            }
            view.showDetailAwayTeam(data.await().teams)
            view.hideLoading()
        }
    }
}