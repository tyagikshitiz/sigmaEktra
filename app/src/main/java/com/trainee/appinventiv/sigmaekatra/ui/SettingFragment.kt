package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_arrow_language.setOnClickListener {
                   findNavController().navigate(R.id.action_settingFragment_to_chooseLanguageSettingFragment)
        }
        iv_arrow_about_us.setOnClickListener {
                       findNavController().navigate(R.id.action_settingFragment_to_aboutUsFragment)
        }
        iv_arrow_faq.setOnClickListener {
                       findNavController().navigate(R.id.action_settingFragment_to_faqFragment)
        }
        iv_arrow_terms.setOnClickListener {
                       findNavController().navigate(R.id.action_settingFragment_to_termsAndConditionFragment)
        }
    }


}