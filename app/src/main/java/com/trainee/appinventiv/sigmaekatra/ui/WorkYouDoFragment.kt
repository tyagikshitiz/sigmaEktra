package com.trainee.appinventiv.sigmaekatra.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.MainActivity
import com.trainee.appinventiv.sigmaekatra.NetworkResult
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.model.UserDetails
import com.trainee.appinventiv.sigmaekatra.model.WorkExperience
import com.trainee.appinventiv.sigmaekatra.util.Preference
import com.trainee.appinventiv.sigmaekatra.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_work_you_do.*

@AndroidEntryPoint
class WorkYouDoFragment : Fragment() {
    private val viewModel:MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_you_do, container, false)
    }

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tv_next_work_you_do.setOnClickListener {
            val navHostFragment = parentFragment as NavHostFragment?
            navHostFragment!!.parentFragment?.view?.
                findNavController()?.navigate(R.id.action_creatingAccountStep1_to_bottomNavigationFrgment)
//            workdetailapi()


        }

    }
    @SuppressLint("LongLogTag")
    fun workdetailapi(){
        val checkedList=ArrayList<String>()
        if (checkbox1.isChecked==true){
            checkedList.add(checkbox1.text.toString())

        }

        if (checkbox2.isChecked==true){
            checkedList.add(checkbox2.text.toString())
        }

        if (checkbox3.isChecked==true){
            checkedList.add(checkbox3.text.toString())
        }

        if (checkbox4.isChecked==true){
            checkedList.add(checkbox4.text.toString())
        }

        if (checkbox5.isChecked==true){
            checkedList.add(checkbox5.text.toString())
        }
        Log.d("arrayalist23", checkedList.toString())
        val user=arguments?.getParcelable<UserDetails>("Key")
        Log.d("workExperience",user.toString())
        val list = ArrayList<Int>()
        list.add(user!!.previousSalary)
        val workExperience= WorkExperience(user!!.education,user.expectedSalary,user.isPreWorkExp,user.jobCategory,user.preferredLocation,list,user.typeOfPreWorkExp,checkedList)
        val pref= Preference(requireActivity())
        val token = pref.getPreference().toString()
//        Log.e(" Registration success data", it.data.toString())
        // viewModel.createWork(token, MainActivity.deviceId,workExperience)
//        Toast.makeText(
//            requireContext(),
//            it.data?.message.toString(),
//            Toast.LENGTH_SHORT
//        ).show()
        Log.d("Shubham",workExperience.toString())

        viewModel.homeLiveData.observe(requireActivity()) {
            when (it) {
                is NetworkResult.Success -> {
                    val navHostFragment = parentFragment as NavHostFragment?
                    navHostFragment!!.parentFragment?.view?.findNavController()?.navigate(R.id.action_creatingAccountStep1_to_bottomNavigationFrgment)

                }
                is NetworkResult.Error -> {
                    Log.e("Registration error details", it.message.toString())
                }
                is NetworkResult.Loading -> {
                    Log.e("Registration data is loading", " load")
                }
                else -> {
                    Log.e("that i no what", " but these it run")
                }
            }
        }
    }

}


