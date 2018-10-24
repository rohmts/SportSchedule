package com.rohmats.kotlinsport.views.home.fragment


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson

import com.rohmats.kotlinsport.R
import com.rohmats.kotlinsport.R.color.colorAccent
import com.rohmats.kotlinsport.api.ApiRepository
import com.rohmats.kotlinsport.config.Constants
import com.rohmats.kotlinsport.model.Event
import com.rohmats.kotlinsport.presenter.home.EventPresenter
import com.rohmats.kotlinsport.views.home.EventView
import com.rohmats.kotlinsport.views.detail.DetailEventActivity
import com.rohmats.kotlinsport.views.home.adapter.EventAdapter
import com.rohmats.kotlinsport.utilities.invisible
import com.rohmats.kotlinsport.utilities.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NextEventFragment : Fragment(), AnkoComponent<Context>, EventView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    private lateinit var listEvent: RecyclerView

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoading()

        adapter = EventAdapter(events) {
            ctx.startActivity<DetailEventActivity>("eventId" to "${it.eventId}")
        }
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)
        presenter.getNextEventList(Constants.LEAGUE_ID_PREMIER_LEAGUE)

        swipeRefreshLayout.onRefresh {
            presenter.getNextEventList(Constants.LEAGUE_ID_PREMIER_LEAGUE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_blue_bright,
                    android.R.color.holo_orange_dark)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = R.id.list_event
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
