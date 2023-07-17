package com.trainee.appinventiv.sigmaekatra.ui

import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.*
import com.trainee.appinventiv.sigmaekatra.databinding.FragmentAbcBinding
import com.trainee.appinventiv.sigmaekatra.model.CurrentLocation
import com.trainee.appinventiv.sigmaekatra.model.UserDetail
import com.trainee.appinventiv.sigmaekatra.util.Preference
import com.trainee.appinventiv.sigmaekatra.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_abc.*
import kotlinx.android.synthetic.main.fragment_creating_account_step1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class AbcFragment : Fragment() {

    //live data


    private var gender:Int = 0
    private lateinit var viewModel: MainActivityViewModel
//    private var latiude:Float=12.36F
//        private var longitude:Float=12.36F
    private var location:String="dwe"

//    private lateinit var category: Int
    var CurrentProgress:Int=0
//    lateinit var progressBar: ProgressBar
//    lateinit var binding01: FragmentCreatingAccountStep1Binding
    private val obj1=CreatingAccountStep1()
    lateinit var binding: FragmentAbcBinding

    var myearr: Int = 0
    var date: String = ""
    var month: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding= FragmentAbcBinding.inflate(inflater,container,false)

        return binding.root

    }

      @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          initViewModel()
          binding.ssshhhhh = viewModel
          binding.lifecycleOwner = this
//          val f = AbcFragment()
//          f.location
//    val x= arguments?.getString("hello").toString()
          //arguments?.getString("hello").toString()!="null")

          var currentLocation=arguments?.getParcelable<CurrentLocation>("hello")




          if (currentLocation!=null){

              CoroutineScope(Dispatchers.Main).launch {
                  delay(100)
                  et_location.setText(currentLocation.location)

              }

          }

//          Log.d("kshitiz", arguments?.getString("hello").toString())


          iv_location.setOnClickListener {
              var bundle =Bundle()
              var category =arguments?.getParcelable<CurrentLocation>("hello")
                  bundle.putString("usertype",category?.category.toString())
              Log.d("childdetail",category.toString())


              if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                  val navHostFragment = parentFragment as NavHostFragment?
                  navHostFragment!!.parentFragment?.view?.findNavController()?.navigate(R.id.action_creatingAccountStep1_to_locationFragment2,bundle)
              }
              else {
                  ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),1)
              }
//
//              val navHostFragment = parentFragment as NavHostFragment?
//              navHostFragment!!.parentFragment?.view?.findNavController()?.navigate(R.id.action_creatingAccountStep1_to_locationFragment2)

          }





          tv_gender_male.setOnClickListener {
              gender=1
              tv_gender_male.isActivated = true
              tv_gender_female.isActivated = false
              tv_gender_other.isActivated = false
              tv_gender_male.setTextColor(Color.parseColor("#ffffff"))
              tv_gender_female.setTextColor(Color.parseColor("#8D98A1"))
              tv_gender_other.setTextColor(Color.parseColor("#8D98A1"))
              iv_male.isActivated = true
              iv_female.isActivated = false
              iv_other.isActivated = false
          }
          tv_gender_female.setOnClickListener {
              gender=2
              tv_gender_male.isActivated = false
              tv_gender_female.isActivated = true
              tv_gender_other.isActivated = false
              tv_gender_male.setTextColor(Color.parseColor("#8D98A1"))
              tv_gender_female.setTextColor(Color.parseColor("#ffffff"))
              tv_gender_other.setTextColor(Color.parseColor("#8D98A1"))
              iv_male.isActivated = false
              iv_female.isActivated = true
              iv_other.isActivated = false
          }
          tv_gender_other.setOnClickListener {
              gender=3
              tv_gender_male.isActivated = false
              tv_gender_female.isActivated = false
              tv_gender_other.isActivated = true
              tv_gender_male.setTextColor(Color.parseColor("#8D98A1"))
              tv_gender_female.setTextColor(Color.parseColor("#8D98A1"))
              tv_gender_other.setTextColor(Color.parseColor("#ffffff"))
              iv_male.isActivated = false
              iv_female.isActivated = false
              iv_other.isActivated = false
          }
          et_email.addTextChangedListener(watcher)

//        iv_back_creating_account.setOnClickListener {
//            findNavController().navigate(R.id.action_creatingAccountStep1_to_ekatraAccountFragment)
//        }
          et_dob.setOnClickListener {
              dob()
          }
          iv_dob.setOnClickListener {
              dob()
          }
          iv_dob1.setOnClickListener {
             dob()
          }
          location=arguments?.getString("location").toString()
          Log.d("hello",location)


          et_location.setText(location)



          tv_details_next_light1.setOnClickListener {
              val category =arguments?.getParcelable<CurrentLocation>("hello")

              val data:UserDetail= UserDetail(category?.lattitude.toString(),category?.longitude.toString(),category?.category.toString(),et_name.text.toString(),et_dob.text.toString(),et_email.text.toString())
              val bundle=Bundle()
              bundle.putParcelable("data",data)
              Log.d("vishal",data.toString())
              findNavController().navigate(R.id.action_abcFragment_to_residenceAddressFragment2,bundle)
//              bundle.putParcelable("data",data)
//             var  category= arguments?.getInt("category")!!

              //   var category =arguments?.getParcelable<CurrentLocation>("usertype")
        Log.d("inside",category?.category.toString())
              Log.d("tyagi",category.toString())

//              latiude= arguments?.getString("lattiude").toString().toFloat()
//              longitude=arguments?.getString("longitude").toString().toFloat(


           //   val createProfile =createProfile(et_name.text.toString(),et_dob.text.toString(),gender,et_email.text.toString(),category?.lattitude!!.toFloat(),category?.longitude!!.toFloat(),category?.category!!.toInt())
//          Log.d("category",category.category.toString())
              val pref=Preference(requireActivity())
           val token = pref.getPreference().toString()
            // val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MjgyMzZiODQxZjI2Y2NiODBlZWRjZDQiLCJzZXNzaW9uSWQiOiI2MjgzNjlkYTM2MDA2MDM2YzE0MThhZjMiLCJ0eXBlIjoiMSIsImlhdCI6MTY1Mjc3OTQ4Mn0.jWncFXdPn5RoHSrTV-8deFs8WpC662iHfUymbrAbQgo"
              Log.d("token",token)
           // viewModel.profileCreate(token,MainActivity.deviceId,createProfile)

      //       findNavController().navigate(R.id.action_abcFragment_to_residenceAddressFragment)
              //              CurrentProgress=CurrentProgress + 10
//          obj1.progressBar.setProgress(100)
//              progressBar?.setMax(100)

          }
          viewModel.profileResponseLiveData.observe(requireActivity(),androidx.lifecycle.Observer {
              if (it.body()?.statusCode==200){

//                    CoroutineScope(Dispatchers.Main).launch {
//                        delay(2000)
                  lifecycleScope.launchWhenResumed {
                      findNavController().navigate(R.id.action_abcFragment_to_residenceAddressFragment2)
//                    }
                  }

              }
              else{
                  Toast.makeText(requireActivity(), "Invalid Otp", Toast.LENGTH_SHORT).show()
              }
          })


      }


    val watcher=object: TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (et_email.text.toString().trim().length>=1)
            {
                tv_optional.visibility=View.GONE
            }
            else{
                tv_optional.visibility=View.VISIBLE
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }
    private fun initViewModel() {
        viewModel= ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        Log.e("view model"," $viewModel + ...................................................................")


    }

    fun dob()
    {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val days = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                this.month = mMonth + 1
                date = "${this.month}/$mDay/$mYear"
                myearr = mYear
                et_dob.text = date
                iv_dob.visibility=View.INVISIBLE
                iv_dob1.visibility=View.VISIBLE

            },
            year,
            month,
            days
        )
        dpd.show()
    }





}