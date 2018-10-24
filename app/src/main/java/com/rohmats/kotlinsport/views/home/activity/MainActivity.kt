package com.rohmats.kotlinsport.views.home.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.rohmats.kotlinsport.R
import com.rohmats.kotlinsport.views.home.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle(R.string.main_title)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.black))
        setSupportActionBar(toolbar)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        this.view_pager.adapter = viewPagerAdapter
        this.tab_layout.setSelectedTabIndicatorColor(Color.parseColor("#3bca3b"));
        this.tab_layout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#3bca3b"));
        this.tab_layout.setupWithViewPager(this.view_pager)


    }

}
