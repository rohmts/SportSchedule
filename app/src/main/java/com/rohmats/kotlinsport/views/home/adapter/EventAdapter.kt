package com.rohmats.kotlinsport.views.home.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rohmats.kotlinsport.R
import com.rohmats.kotlinsport.R.id.*
import com.rohmats.kotlinsport.model.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rohmats on 21/10/18.
 */

class EventAdapter(
    private val events: List<Event>,
    private val listener: (Event) -> Unit) : RecyclerView.Adapter<EventViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EventViewHolder {
        return EventViewHolder(
            EventUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

}

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val eventDate: TextView = view.find(event_date)
    private val eventTime: TextView = view.find(event_time)
    private val eventHomeTeamName: TextView = view.find(event_home_team_name)
    private val eventAwayTeamName: TextView = view.find(event_away_team_name)
    private val eventHomeScore: TextView = view.find(event_int_home_score)
    private val eventAwayScore: TextView = view.find(event_int_away_score)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(events: Event, listener: (Event) -> Unit) {

        //date format
        var defaultDateFormat = SimpleDateFormat("dd/MM/yy")
        val dateFormat: Date = defaultDateFormat.parse(events.eventDate)
        defaultDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
        val date: String = defaultDateFormat.format(dateFormat)

        //time format
        var defaultTimeFormat = SimpleDateFormat("HH:mm:ssZ")
        val timeFormat: Date = defaultTimeFormat.parse(events.eventTime)
        defaultTimeFormat = SimpleDateFormat("HH:mm aa", Locale.US)
        val time: String = defaultTimeFormat.format(timeFormat)

        eventDate.text = date
        eventTime.text = time
        eventHomeTeamName.text = events.eventHomeTeamName
        eventAwayTeamName.text = events.eventAwayTeamName
        eventHomeScore.text = events.eventHomeScore
        eventAwayScore.text = events.eventAwayScore

        itemView.onClick { listener(events) }
    }
}

class EventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL

                lparams(width = matchParent, height = wrapContent) {
                    marginStart = dip(8)
                    marginEnd = dip(8)
                    bottomMargin = dip(24)
                }
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    textView {
                        id = event_date
                        textSize = 12f
                        textColor = ContextCompat.getColor(context, R.color.colorAccent)

                }.lparams(width = wrapContent, height = wrapContent) {
                        marginEnd = dip(8)
                    }

                    textView {
                        id = event_time
                        textSize = 12f
                        textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    }
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(16)
                    }

                    textView {
                        id = event_home_team_name
                        textSize = 16f
                        gravity = Gravity.END
                        maxLines = 1
                        textColor = ContextCompat.getColor(context, R.color.colorTextPrimary)
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams{
                        weight = 2f
                        width = 0
                        marginEnd = dip(16)
                    }

                    textView {
                        id = event_int_home_score
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = ContextCompat.getColor(context, R.color.colorTextPrimary)
                        this.gravity = Gravity.END
                    }.lparams(width = wrapContent, height = wrapContent) {
                        marginEnd = dip(8)
                    }

                    textView {
                        text = "vs"
                        textColor = ContextCompat.getColor(context, R.color.colorTextPrimary)
                    }.lparams(width = wrapContent, height = wrapContent) {

                    }

                    textView {
                        id = event_int_away_score
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = ContextCompat.getColor(context, R.color.colorTextPrimary)
                    }.lparams(width = wrapContent, height = wrapContent){
                        marginStart = dip(8)
                    }

                    textView {
                        id = event_away_team_name
                        textSize = 16f
                        maxLines = 1
                        ellipsize = TextUtils.TruncateAt.END
                        textColor = ContextCompat.getColor(context, R.color.colorTextPrimary)
                    }.lparams{
                        weight = 2f
                        width = 0
                        marginStart = dip(16)
                    }
                }
            }
        }
    }

}