package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation_frgment.*
import kotlinx.android.synthetic.main.fragment_creating_account_step1.*
import kotlinx.android.synthetic.main.fragment_jobs_detail.*


class JobsDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobs_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bottom_nav_view.visibility=View.INVISIBLE
        tv_details_next_light.setOnClickListener {
            tv_details_next_light.visibility=View.GONE
            tv_details_next_apllied.visibility=View.VISIBLE
            iv_call_disabled.visibility=View.GONE
            iv_call_enabled.visibility=View.VISIBLE

        }

    }

}