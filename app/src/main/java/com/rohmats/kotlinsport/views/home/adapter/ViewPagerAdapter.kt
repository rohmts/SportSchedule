package com.rohmats.kotlinsport.views.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.rohmats.kotlinsport.views.home.fragment.LastEventFragment
import com.rohmats.kotlinsport.views.home.fragment.NextEventFragment

/**
 * Created by Rohmats on 21/10/18.
 */

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        if (position == 0) {
            fragment = NextEventFragment()
        } else if (position == 1) {
            fragment = LastEventFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Next Match"
        }
        else if (position == 1) {
            title = "Last Match"
        }
        return title
    }

}