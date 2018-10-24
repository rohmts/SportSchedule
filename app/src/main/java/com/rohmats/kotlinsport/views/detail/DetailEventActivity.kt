package com.rohmats.kotlinsport.views.detail

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.rohmats.kotlinsport.R
import com.rohmats.kotlinsport.R.color.colorAccent
import com.rohmats.kotlinsport.api.ApiRepository
import com.rohmats.kotlinsport.config.Constants
import com.rohmats.kotlinsport.model.Event
import com.rohmats.kotlinsport.model.Team
import com.rohmats.kotlinsport.presenter.detail.EventDetailPresenter
import com.rohmats.kotlinsport.presenter.home.EventPresenter
import com.rohmats.kotlinsport.utilities.invisible
import com.rohmats.kotlinsport.utilities.visible
import com.rohmats.kotlinsport.views.home.EventView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_event.*
import kotlinx.android.synthetic.main.content_detail_event.*
import org.jetbrains.anko.support.v4.onRefresh
import java.text.SimpleDateFormat
import java.util.*

class DetailEventActivity : AppCompatActivity(), DetailEventView {

    private lateinit var teams: Team
    private lateinit var events: Event
    private lateinit var presenter: EventDetailPresenter

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var eventId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        val toolbar = findViewById<View>(R.id.toolbar_detail_event) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        eventId = intent.getStringExtra("eventId")

        initView()
        showLoading()
        setPresenterEvent()

        swipeRefreshLayout.onRefresh {
            setPresenterEvent()
        }

    }
    override fun initView() {

        progressBar = findViewById(R.id.progress_bar)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setColorSchemeResources(
            colorAccent,
            android.R.color.holo_blue_bright,
            android.R.color.holo_orange_dark
        )
    }

    fun setPresenterEvent() {
        presenter = EventDetailPresenter(this, ApiRepository(), Gson())
        presenter.getDetailEvent(eventId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    @SuppressLint("SimpleDateFormat")
    override fun showDetailEvent(data: List<Event>) {

        events = Event(data[0].eventHomeTeamName, data[0].eventAwayTeamName)

        swipeRefreshLayout.isRefreshing = false

        presenter.getDetailHomeTeam(data[0].eventHomeTeamId)
        presenter.getDetailAwayTeam(data[0].eventAwayTeamId)

        //date format
        var defaultDateFormat = SimpleDateFormat("dd/MM/yy")
        val dateFormat: Date = defaultDateFormat.parse(data[0].eventDate)
        defaultDateFormat = SimpleDateFormat("EEE, dd MMM yyyy  ")
        val date: String = defaultDateFormat.format(dateFormat)

        //time format
        var defaultTimeFormat = SimpleDateFormat("HH:mm:ssZ")
        val timeFormat: Date = defaultTimeFormat.parse(data[0].eventTime)
        defaultTimeFormat = SimpleDateFormat("HH:mm aa", Locale.US)
        val time: String = defaultTimeFormat.format(timeFormat)

        tv_detail_event_date.text = date+time
        tv_detail_home_score.text = data[0].eventHomeScore
        tv_detail_away_score.text = data[0].eventAwayScore
        tv_home_team_name.text = data[0].eventHomeTeamName
        tv_away_team_name.text = data[0].eventAwayTeamName

        //string format
        val homeGoalDetails: String? = data[0].eventHomeGoalDetails?.replace(":", " ")?.replace(";","\n")
        val awayGoalDetails: String? = data[0].eventAwayGoalDetails?.replace(":"," ")?.replace(";", "\n")
        val homeGoalkeeper: String? = data[0].eventHomeLineupGk?.replace(";","")
        val awayGoalkeeper: String? = data[0].eventAwayLineupGk?.replace(";","")
        val homeDefender: String? = data[0].eventHomeLineupDefense?.replace("; ","\n")
        val awayDefender: String? = data[0].eventAwayLineupDefense?.replace("; ","\n")
        val homeMidfield: String? = data[0].eventHomeLineupMidfield?.replace("; ","\n")
        val awayMidfield: String? = data[0].eventAwayLineupMidfield?.replace("; ","\n")
        val homeForward: String? = data[0].eventHomeLineupForward?.replace("; ","\n")
        val awayForward: String? = data[0].eventAwayLineupForward?.replace("; ","\n")
        val homeSubstitutes: String? = data[0].eventHomeLineupSubs?.replace("; ","\n")
        val awaySubstitutes: String? = data[0].eventAwayLineupSubs?.replace("; ","\n")

        if (data[0].eventHomeGoalDetails != null) {
            tv_home_goal_details.text = homeGoalDetails
        }
        if (data[0].eventAwayGoalDetails != null) {
            tv_away_goal_details.text = awayGoalDetails
        }
        if (data[0].eventHomeShots != null) {
            tv_home_shots.text = data[0].eventHomeShots
        }
        if (data[0].eventAwayShots != null) {
            tv_away_shots.text = data[0].eventAwayShots
        }
        tv_home_goalkeeper.text = homeGoalkeeper
        tv_away_goalkeeper.text = awayGoalkeeper
        tv_home_defense.text = homeDefender
        tv_away_defense.text = awayDefender
        tv_home_midfield.text = homeMidfield
        tv_away_midfield.text = awayMidfield
        tv_home_forward.text = homeForward
        tv_away_forward.text = awayForward
        tv_home_substitutes.text = homeSubstitutes
        tv_away_substitutes.text = awaySubstitutes
    }

    override fun showDetailHomeTeam(data: List<Team>) {
        this.teams = Team(data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(iv_detail_home_team_badge)
    }

    override fun showDetailAwayTeam(data: List<Team>) {
        this.teams = Team(data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(iv_detail_away_team_badge)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
