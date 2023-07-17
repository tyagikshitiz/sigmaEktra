package com.trainee.appinventiv.sigmaekatra.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.ui.JobTablayoutFragment
import kotlinx.android.synthetic.main.jobs_card.view.*

class JobAdapter(private val navController: NavController,val x:Int):RecyclerView.Adapter<JobAdapter.ResultViewHolder>() {
    class ResultViewHolder(itemview: View) :RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.jobs_card,parent,false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        Log.d("abcd", x.toString())
        holder.itemView.setOnClickListener {
            when (x) {
                1 -> {
                    navController.navigate(R.id.action_homeFragment2_to_jobsDetailFragment2)
                }
                2 -> {
//                    move.MoveFrag(x)
                  //  val navHostFragment = parentFragment as NavHostFragment?
                    //navHostFragment!!.parentFragment?.view?.findNavController()?.navigate(R.id.action_jobTablayoutFragment_to_jobsDetailFragment2)
                    navController.navigate(R.id.action_bottomNavigationFrgment_to_jobsDetailFragment3)


                }
                3 -> {
                    navController.navigate(R.id.action_bottomNavigationFrgment_to_jobsDetailFragment3)
                }
                else -> {
                    navController.navigate(R.id.action_bottomNavigationFrgment_to_jobsDetailFragment3)

                }
            }
        }

//     val array =array[position]

    }

    override fun getItemCount(): Int {
      return 10
    }
}