package com.trainee.appinventiv.sigmaekatra.ui

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.MainActivity
import com.trainee.appinventiv.sigmaekatra.OtpVerification
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.User
import com.trainee.appinventiv.sigmaekatra.databinding.FragmentLoginBinding
import com.trainee.appinventiv.sigmaekatra.util.Preference
import com.trainee.appinventiv.sigmaekatra.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_abc.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.otp_view.*
import kotlinx.android.synthetic.main.otp_view.view.*
import java.util.*
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    var binding: FragmentLoginBinding?=null
    var otp:String?=null
    var timer:TextView?=null
    var resendotp:TextView?=null
    //val tv8 =dialog.findViewById(R.id.tv_resendCode) as TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


       binding= FragmentLoginBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_condition.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_termsAndConditionFragment2)
        }
        initViewModel()
        binding?.etPhoneNo?.addTextChangedListener(watcher)

        val user= User("91"+et_phone_no.text.toString())
        iv_back.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }


            tv_submit.setOnClickListener {
                createUser()

                  openDialog()
            }


    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }
    val watcher=object:TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (et_phone_no.text.toString().trim().length==10)
            {
                tv_submit1.visibility=View.INVISIBLE
                tv_submit.visibility=View.VISIBLE

            }
            else{
                tv_submit1.visibility=View.VISIBLE
                tv_submit.visibility=View.INVISIBLE

            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun initViewModel() {
    viewModel=ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        Log.e("view model"," $viewModel + ...................................................................")


    }

    private fun createUser() {
      val user= User("91"+et_phone_no.text.toString())
        viewModel.createNewUser(user)
    }

    private fun openDialog() {

        val view = View.inflate(requireContext(), R.layout.otp_view, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        var dialog = builder.create()
        otpTimer()


        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.getWindow()?.setGravity(Gravity.BOTTOM)
        dialog.window?.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        val editTextArray = arrayOf(
            view.et_otp_digit1,
          view.et_otp_digit2,
       view.et_otp_digit3,
            view.et_otp_digit4,

        )
        changeFocus(editTextArray)
        deleteCode(editTextArray)
        otpTimer()


      val tv41 =dialog.findViewById(R.id.tv_verify_phone) as TextView
        val tv =dialog.findViewById(R.id.tv_submit_otp) as TextView
        val tv1 = dialog.findViewById(R.id.et_otp_digit1) as EditText
        val tv2 = dialog.findViewById(R.id.et_otp_digit2) as EditText
        val tv3 = dialog.findViewById(R.id.et_otp_digit3) as EditText
        val tv4 = dialog.findViewById(R.id.et_otp_digit4) as EditText
        timer = dialog.findViewById(R.id.tv_timer) as TextView
    resendotp =dialog.findViewById(R.id.tv_resendCode) as TextView



    tv.setOnClickListener {
            otp=tv1.text.toString()+tv2.text.toString()+tv3.text.toString()+tv4.text.toString()
      //      Toast.makeText(requireActivity(), "clicked", Toast.LENGTH_SHORT).show()
            val verifyOtp=OtpVerification("91"+et_phone_no.text.toString(),otp)
            Log.e("button","clicked")
            viewModel?.verifyotp(MainActivity.deviceId,verifyOtp)

         dialog.dismiss()

       }

        viewModel.otpVerificationResponse2.observe(requireActivity(),androidx.lifecycle.Observer {
            if (it.body()?.statusCode==200){
                val preference = Preference(requireActivity())
                preference.savePreference(it.body()?.data?.token)
                findNavController().navigate(R.id.action_loginFragment_to_chooseModeFragment)
            }
            else{
                Toast.makeText(requireActivity(), "Invalid Otp", Toast.LENGTH_SHORT).show()
            }
        })


    }
    fun otpVerification(){
        findNavController().navigate(R.id.action_loginFragment_to_chooseModeFragment)

    }


    private fun changeFocus(editTextArray: Array<EditText>) {
        for (i in 0 until editTextArray.size-1)
        otp(editTextArray[i],editTextArray[i+1])

    }

    private fun otp(currentText: EditText, nextText: EditText) {
    currentText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
        if (currentText.length()==1)
            nextText.requestFocus()
        }
    })
    }

    private fun deleteCode(editTextArray: Array<EditText>) {
        for (i in editTextArray.indices) {

            if (i == 0) {
                codeDelete(editTextArray[i], editTextArray[i])
            } else {
                codeDelete(editTextArray[i], editTextArray[i - 1])
            }
        }

    }

    private fun codeDelete(currentText: EditText, nextText: EditText) {
        currentText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p2!!.action == KeyEvent.ACTION_DOWN &&
                    p1 == KeyEvent.KEYCODE_DEL &&
                    currentText.id != R.id.et_otp_digit1 &&
                    currentText.text.isEmpty()
                ) {

                    //If current is empty then previous EditText's number will also be deleted
                    nextText!!.text = null
                    nextText.requestFocus()
                    return true


                }
                return false
            }
        })
    }
    private fun otpTimer() {
        resendOTPNoClickable()
        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val secs = millisUntilFinished / 1000
                //  binding.tvResendCode.visibility = View.INVISIBLE
                timer?.text = String.format(
                    Locale.getDefault(),
                    "%02d:%02d",
                    0, secs
                )
            }

            override fun onFinish() {
             resendotp?.setTextColor(Color.parseColor("#21317B"))
                resendOTPClickable()
            }

        }.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun resendOTPNoClickable() {
       resendotp?.setOnTouchListener { p0, p1 ->
            Log.e("msg", "first")
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun resendOTPClickable() {
      resendotp?.setOnTouchListener { p0, p1 ->
          createUser()
          otpTimer()
            Log.e("msg", "second")
            false
        }
    }

}