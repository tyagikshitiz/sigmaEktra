package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.model.UserDetail
import kotlinx.android.synthetic.main.fragment_residence_address.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResidenceAddressFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_residence_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(100)
            val userInfo = arguments?.getParcelable<UserDetail>("data")
            Log.d("UserInfo", userInfo.toString())
        }
        tv_details_next_light_district1.setOnClickListener {
            findNavController().navigate(R.id.action_residenceAddressFragment_to_imageUploadFragment)
        }
    }


}