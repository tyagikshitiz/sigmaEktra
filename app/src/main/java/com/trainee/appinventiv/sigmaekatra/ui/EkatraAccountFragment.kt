package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import kotlinx.android.synthetic.main.fragment_ekatra_account.*


class EkatraAccountFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_ekatra_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        iv_back_ekatra.setOnClickListener {
            findNavController().navigate(R.id.action_ekatraAccountFragment_to_chooseModeFragment)
           // findNavController().navigate(bac)
        }
        tv_lets_start.setOnClickListener {
            val category=arguments?.getString("category")
            var bundle=Bundle()
                bundle.putString("category",category)
            Log.d("zbook",category.toString())

            findNavController().navigate(R.id.action_ekatraAccountFragment_to_creatingAccountStep1,bundle)
        }
    }


}