package com.toddburgessmedia.randomcatkotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter internal constructor(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    val COUNT = 2

    override fun getItem(position: Int): Fragment? {

        var fragment : Fragment? = null

        when(position) {
            0 -> fragment = MainActivityFragment()
            1 -> fragment = AllPhotosFragment()
        }

        return fragment
    }

    override fun getCount(): Int = COUNT

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null

        when (position) {
            0 -> title = "Random Photo"
            1 -> title = "All Photos"
        }

        return title
    }
}