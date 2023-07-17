package com.trainee.appinventiv.sigmaekatra.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.model.CurrentLocation
import com.trainee.appinventiv.sigmaekatra.util.Preference
import com.trainee.appinventiv.sigmaekatra.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_abc.*
import kotlinx.android.synthetic.main.fragment_creating_account_step1.*
import java.util.*
import java.util.logging.Level.parse
import kotlin.collections.ArrayList
@AndroidEntryPoint
class CreatingAccountStep1 : Fragment() {
  //  private lateinit var controller: NavController // don't forget to initialize
  private lateinit var viewModel: MainActivityViewModel
    var myearr: Int = 0
    var date: String = ""
    var month: Int = 0
    var arr= ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_creating_account_step1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var category=arguments?.getString("category")
        Log.d("parent",category.toString())
        var latiude= arguments?.getString("lattitude").toString()
       var  longitude=arguments?.getString("longitude").toString()

        Log.d("place", latiude.toString())
       val location=arguments?.getString("location").toString()
        Log.d("hello",location)
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.first) as NavHostFragment

//        navHostFragment.arguments?.putInt("hello", 4)
        val k= arguments?.getParcelable<CurrentLocation>("usertype")
        Log.d("oneplus",k.toString())
        val bundle = Bundle()
        if(k==null){
            val currentLocation=CurrentLocation(latiude.toString(),longitude.toString(),location.toString(),category.toString())
            bundle.putParcelable("hello",currentLocation)
        }
        else{
            bundle.putParcelable("hello",k)
        }
        navHostFragment.findNavController().setGraph(R.navigation.navigation2,bundle)  // bundleOf("hello" to currentLocation)

//        viewModel.profileCreate()

        var percent = 0

        navHostFragment.findNavController().addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{

            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Log.d("destination", destination.displayName)



                Log.d("arr size", arr.size.toString())
                Log.d("arr",arr.toString())
                if (arr.size>1 && arr.get(arr.size-2)==destination.displayName){
                    percent -=20
                    arr.removeAt(arr.size-1)
                    Log.d("minus", destination.displayName.toString() + arr)

                    progressBar.progress=percent
                }
                else{
                    percent +=20
                    arr.add(destination.displayName)
                    progressBar.progress=percent
                    Log.d("plus", destination.displayName + arr.size)
                }


            }

        })

//        val navController = navHostFragment.navController
//        tv_details_next_light1.setOnClickListener {
//            findNavController().navigate(R.id.action_abcFragment_to_imageUploadFragment2)
//            navController.navigate(R.id.action_abcFragment_to_imageUploadFragment2)
//        }
//    val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
//           progressBar.setProgress(100)
//        }
//     fun onResume() {
//            super.onResume()
//            controller.addOnDestinationChangedListener(listener)
//        }
//
//        override fun onPause() {
//            controller.removeOnDestinationChangedListener(listener)
//            super.onPause()
//        }
    }
//        val listener =
//            NavController.OnDestinationChangedListener { controller, destination, arguments ->
//                Log.d("destination", destination.displayName)
//                progressBar.setProgress(10)
//
//            }
//
//
//        override fun onResume() {
//            super.onResume()
//            findNavController().addOnDestinationChangedListener(listener)
//
//        }
//
//        override fun onPause() {
//            findNavController().addOnDestinationChangedListener(listener)
//
//            super.onPause()
//        }

//
//    fun navigate(){
//
//       findNavController().navigate(R.id.action_creatingAccountStep1_to_locationFragment2)
//    }


}