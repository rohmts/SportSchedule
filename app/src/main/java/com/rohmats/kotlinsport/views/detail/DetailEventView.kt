package com.rohmats.kotlinsport.views.detail

import com.rohmats.kotlinsport.model.Event
import com.rohmats.kotlinsport.model.Team

/**
 * Created by Rohmats on 22/10/18.
 */

interface DetailEventView {
    fun initView()
    fun showLoading()
    fun hideLoading()
    fun showDetailEvent(data: List<Event>)
    fun showDetailHomeTeam(data: List<Team>)
    fun showDetailAwayTeam(data: List<Team>)
}