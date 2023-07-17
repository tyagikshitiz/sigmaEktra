package com.trainee.appinventiv.sigmaekatra.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.adapter.JobAdapter
import com.trainee.appinventiv.sigmaekatra.adapter.Move
import com.trainee.appinventiv.sigmaekatra.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.rv_jobs
import kotlinx.android.synthetic.main.fragment_jobs.*

class JobsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val al=ArrayList<String>()
//        al.add("All Jobs")
//        al.add(("Interested Jobs"))
//        al.add("Applied Jobs")
//
        rv_jobs.layoutManager= LinearLayoutManager(requireActivity())
        val adapter = JobAdapter(findNavController(),2)
        rv_jobs.adapter=adapter
    }
}