package com.trainee.appinventiv.sigmaekatra.ui

import android.content.res.Resources
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
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_bottom_navigation_frgment.*
import kotlinx.android.synthetic.main.fragment_jobs.*


class BottomNavigationFrgment : Fragment() {
  private lateinit var navController: NavController
    private lateinit var navHostFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_bottom_navigation_frgment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

     navHostFragment = childFragmentManager.findFragmentById(R.id.second) as NavHostFragment

        navController=navHostFragment.findNavController()
        bottom_nav_view.setupWithNavController(navController)
//        val navhostfrag = childFragmentManager.findFragmentById(R.id.second) as NavHostFragment
//        val controller = navhostfrag.navController //  NavigationUI.setupWithNavController(bottom_nav_view, controller)

        super.onViewCreated(view, savedInstanceState)
        bottom_nav_view.setOnItemSelectedListener {
            Log.d("item", it.itemId.toString())
            when (it.itemId) {
                R.id.nav_jobs -> {
               navController.navigate(R.id.action_global_jobTablayoutFragment)
                    true
                }

                    R.id.nav_home->{
                        navController.navigate(R.id.action_global_homeFragment23)
                        true

                }
                R.id.nav_setting->{
                    navController.navigate(R.id.action_global_settingFragment)
                    true
                }
                R.id.nav_profile->{
                    navController.navigate(R.id.action_global_profileFragment)
                    true
                }

                else -> {
                    false
                }
            }
        }


  navHostFragment.findNavController().addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{

            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Log.d("destination",destination.displayName.toString())
                when(destination.id){
                    R.id.homeFragment2->{
                        bottom_nav_view.visibility=View.VISIBLE
                        bottom_nav_view.menu.getItem(0).isChecked = true
                    }
                    R.id.jobTablayoutFragment->{
                        bottom_nav_view.visibility=View.VISIBLE
                        bottom_nav_view.menu.getItem(1).isChecked = true
                    }
                    R.id.profileFragment->{
                        bottom_nav_view.visibility=View.VISIBLE
                        bottom_nav_view.menu.getItem(2).isChecked = true

                    }
                    R.id.settingFragment->{
                        bottom_nav_view.visibility=View.VISIBLE
                        bottom_nav_view.menu.getItem(3).isChecked = true

                    }

                    R.id.jobsDetailFragment2-> {
                        bottom_nav_view.visibility = View.INVISIBLE
                    }
                    else ->{
                        Log.d("hello",destination.displayName)
                        bottom_nav_view.visibility=View.INVISIBLE

                    }
                }


            }

        })


    }

}