package com.trainee.appinventiv.sigmaekatra.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.trainee.appinventiv.sigmaekatra.ui.*

class ViewPagerAdapter(fm:FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle) {
    var fragmentList= arrayOf(JobsFragment(),InterestedJobFragment(),AppliedJobFragment())
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }


}