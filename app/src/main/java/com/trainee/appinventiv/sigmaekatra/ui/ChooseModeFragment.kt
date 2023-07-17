package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import kotlinx.android.synthetic.main.fragment_choose_mode.*

class ChooseModeFragment : Fragment() {
//    private var user: Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_mode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_radio1.setOnClickListener {

            btn_radio1.isChecked = true
        }
        tv_radio2.setOnClickListener {

            btn_radio2.isChecked = true
        }
        tv_radio3.setOnClickListener {

            btn_radio3.isChecked = true
        }



        tv_next.setOnClickListener {
            if (btn_radio1.isChecked){
                var bundle=Bundle()
                bundle.putString("category","1")


             findNavController().navigate(R.id.action_chooseModeFragment_to_ekatraAccountFragment,bundle)

            }
            else if (btn_radio2.isChecked)
            {

       // findNavController().navigate(R.id.action_chooseModeFragment_to_loginFragment)
            }
            else
            {

          //     findNavController().navigate(R.id.action_chooseModeFragment_to_welcomeFragment)
            }
        }
    }


}