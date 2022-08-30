package com.example.weatherapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.ui.ListFragment
import com.example.weatherapp.ui.TodayFragment
import com.example.weatherapp.ui.TomorrowFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return TodayFragment()
            1 -> return TomorrowFragment()
        }
        return ListFragment()
    }
}