package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import kotlinx.android.synthetic.main.fragment_choose_language.*
import kotlinx.android.synthetic.main.fragment_choose_mode.*


class ChooseLanguageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_english.setOnClickListener {
            btn_radio_english.isChecked=true
        }
        tv_hindi.setOnClickListener {
            btn_radio_hindi.isChecked=true
        }
        tv_get_started.setOnClickListener {
            if (btn_radio_english.isChecked){
     findNavController().navigate(R.id.creatingAccountStep1)
 // findNavController().navigate(R.id.action_chooseLanguageFragment_to_tutorialScreen1Fragment2)
 //    findNavController().navigate(R.id.action_chooseLanguageFragment_to_chooseModeFragment)
              //findNavController().navigate(R.id.action_chooseLanguageFragment_to_bottomNavigationFrgment)
              //  findNavController().navigate(R.id.action_chooseLanguageFragment_to_creatingAccountStep1)
            }
            else{
                Toast.makeText(requireActivity(), "under develop", Toast.LENGTH_SHORT).show()
            }
        }


    }


}