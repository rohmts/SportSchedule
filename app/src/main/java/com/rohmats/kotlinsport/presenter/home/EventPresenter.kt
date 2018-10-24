package com.rohmats.kotlinsport.presenter.home

import com.google.gson.Gson
import com.rohmats.kotlinsport.utilities.CoroutineContextProvider
import com.rohmats.kotlinsport.api.ApiRepository
import com.rohmats.kotlinsport.api.TheSportDBApi
import com.rohmats.kotlinsport.model.EventResponse
import com.rohmats.kotlinsport.views.home.EventView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Rohmats on 21/10/18.
 */

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getNextEventList(leagueId: String?) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextEvent(leagueId)),
                    EventResponse::class.java
                )
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }

    fun getLastEventList(leagueId: String?) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastEvent(leagueId)),
                    EventResponse::class.java
                )
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }
}