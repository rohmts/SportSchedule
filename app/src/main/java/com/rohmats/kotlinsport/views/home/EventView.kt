package com.rohmats.kotlinsport.views.home

import com.rohmats.kotlinsport.model.Event

/**
 * Created by Rohmats on 21/10/18.
 */

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}