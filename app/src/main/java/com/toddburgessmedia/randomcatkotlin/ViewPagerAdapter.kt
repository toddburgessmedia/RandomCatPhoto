package com.toddburgessmedia.randomcatkotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter internal constructor(fm : androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    val COUNT = 2

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {

        var fragment : androidx.fragment.app.Fragment? = null

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