package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.adapter.Move
import com.trainee.appinventiv.sigmaekatra.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_bottom_navigation_frgment.*
import kotlinx.android.synthetic.main.fragment_job_tablayout.*

class JobTablayoutFragment : Fragment() {
    private var names = arrayOf("All Jobs", "Interested Jobs", "Applied Jobs")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_tablayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        TabLayoutMediator(tablayout, viewpager) { tab, pos ->
            tab.text = names[pos]

        }.attach()

      //  val navHostFragment = childFragmentManager
        //    .findFragmentById(R.id.second) as NavHostFragment


    }
}