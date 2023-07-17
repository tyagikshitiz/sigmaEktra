package com.trainee.appinventiv.sigmaekatra.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.model.UserDetails
import kotlinx.android.synthetic.main.add_another_layout.*
import kotlinx.android.synthetic.main.fragment_abc.*
import kotlinx.android.synthetic.main.fragment_create_account_work.*
import kotlinx.android.synthetic.main.fragment_create_account_work.et_enter_salary1
import kotlinx.android.synthetic.main.fragment_create_account_work.iv_dropdown1
import kotlinx.android.synthetic.main.fragment_create_account_work.tv_experience2
import kotlinx.android.synthetic.main.fragment_create_account_work.tv_privious_salary1
import kotlinx.android.synthetic.main.fragment_create_account_work.tv_spinner2
import kotlinx.android.synthetic.main.fragment_create_account_work.view.*


class CreateAccountWorkFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var isWorkExperience:Boolean?=null
    private var array=ArrayList<Int>()

    var salary =0
//    lateinit var k: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_create_account_work, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var typeOfWork=tv_spinner1.selectedItemPosition
//        var pereferdLocation=tv_spinner2.selectedItemPosition
//        var jobCategory=tv_spinner_no.selectedItemPosition

//        Log.d("spinner",k.toString())


        //hash map

//        Log.d("spinner",et_expected_salary1.text.toString())




       val adapter=ArrayAdapter.createFromResource(requireActivity(),R.array.education,
           com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        tv_spinner.adapter=adapter
        tv_spinner.onItemSelectedListener=this

        val adapter1=ArrayAdapter.createFromResource(requireActivity(),R.array.workExperience,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        tv_spinner1.adapter=adapter1
        tv_spinner1.onItemSelectedListener=this
        tv_spinner_no.adapter=adapter1
        tv_spinner_no.onItemSelectedListener=this


        val adapter2=ArrayAdapter.createFromResource(requireActivity(),R.array.location,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        tv_location.adapter=adapter2
        tv_location.onItemSelectedListener=this
        tv_location_no.adapter=adapter2
        tv_location_no.onItemSelectedListener=this


        tv_Work_Experience.setOnClickListener {
            isWorkExperience=true
            tv_Work_Experience.isActivated = true
            tv_Work_Experience_no.isActivated = false
            tv_Work_Experience.setTextColor(Color.parseColor("#ffffff"))
            tv_Work_Experience_no.setTextColor(Color.parseColor("#8D98A1"))
            iv_work_experience.isActivated = true
            iv_work_experience_no.isActivated = false

            tv_spinner1.visibility=View.VISIBLE
            tv_experience1.visibility=View.VISIBLE
            tv_privious_salary.visibility=View.VISIBLE
            et_enter_salary.visibility=View.VISIBLE
            iv_dropdown.visibility=View.VISIBLE
            add_another.visibility=View.VISIBLE
            tv_location.visibility=View.VISIBLE
            tv_prefered_location.visibility=View.VISIBLE
            iv_dropdown_location.visibility=View.VISIBLE
            tv_next_yes.visibility=View.VISIBLE
            tv_spinner2.visibility=View.GONE
            tv_privious_salary1.visibility=View.GONE
            tv_experience2.visibility=View.GONE
            et_enter_salary1.visibility=View.GONE
            iv_dropdown1.visibility=View.GONE
          tv_remove.visibility=View.INVISIBLE

            tv_expected_salary1.visibility=View.INVISIBLE
            et_expected_salary1.visibility=View.INVISIBLE
            tv_job_category.visibility=View.INVISIBLE
            tv_spinner_no.visibility=View.INVISIBLE
            iv_dropdown_no.visibility=View.INVISIBLE
            tv_prefered_location_no.visibility=View.INVISIBLE
            tv_location_no.visibility=View.INVISIBLE
            iv_dropdown_location_no.visibility=View.INVISIBLE
            tv_next_no.visibility=View.INVISIBLE


        }
        tv_Work_Experience_no.setOnClickListener {
            isWorkExperience=false
            tv_Work_Experience.isActivated = false
            tv_Work_Experience_no.isActivated = true
            tv_Work_Experience.setTextColor(Color.parseColor("#8D98A1"))
            tv_Work_Experience_no.setTextColor(Color.parseColor("#ffffff"))

            iv_work_experience.isActivated = false
            iv_work_experience_no.isActivated =true
            tv_job_category.visibility=View.VISIBLE
            tv_spinner_no.visibility=View.VISIBLE
            iv_dropdown_no.visibility=View.VISIBLE
            tv_prefered_location_no.visibility=View.VISIBLE
            tv_location_no.visibility=View.VISIBLE
            iv_dropdown_location_no.visibility=View.VISIBLE
            tv_next_no.visibility=View.VISIBLE

            tv_spinner1.visibility=View.INVISIBLE
            tv_experience1.visibility=View.INVISIBLE
            tv_privious_salary.visibility=View.INVISIBLE
            et_enter_salary.visibility=View.INVISIBLE
            iv_dropdown.visibility=View.INVISIBLE
            tv_prefered_location.visibility=View.INVISIBLE
            add_another.visibility=View.INVISIBLE
            tv_location.visibility=View.INVISIBLE
            iv_dropdown_location.visibility=View.INVISIBLE
            tv_next_yes.visibility=View.INVISIBLE

            tv_spinner2.visibility=View.GONE
            tv_privious_salary1.visibility=View.GONE
            tv_experience2.visibility=View.GONE
            et_enter_salary1.visibility=View.GONE
            iv_dropdown1.visibility=View.GONE
           tv_remove.visibility=View.INVISIBLE

            tv_expected_salary1.visibility=View.VISIBLE
            et_expected_salary1.visibility=View.VISIBLE

        }
        add_another.setOnClickListener {
            tv_spinner2.visibility=View.VISIBLE
            tv_privious_salary1.visibility=View.VISIBLE
            tv_experience2.visibility=View.VISIBLE
            et_enter_salary1.visibility=View.VISIBLE
            iv_dropdown1.visibility=View.VISIBLE
            tv_remove.visibility=View.VISIBLE
            add_another.visibility=View.INVISIBLE
            tv_expected_salary1.visibility=View.INVISIBLE
            et_expected_salary1.visibility=View.INVISIBLE
            tv_location.visibility=View.VISIBLE
            tv_prefered_location.visibility=View.VISIBLE
            iv_dropdown_location.visibility=View.VISIBLE
            tv_next_yes.visibility=View.VISIBLE

            tv_job_category.visibility=View.INVISIBLE
            tv_spinner_no.visibility=View.INVISIBLE
            iv_dropdown_no.visibility=View.INVISIBLE
            tv_location_no.visibility=View.INVISIBLE
            tv_prefered_location_no.visibility=View.INVISIBLE
            iv_dropdown_location_no.visibility=View.INVISIBLE
            tv_next_no.visibility=View.INVISIBLE

        }
        tv_remove.setOnClickListener {
            tv_spinner2.visibility=View.GONE
            tv_privious_salary1.visibility=View.GONE
            tv_experience2.visibility=View.GONE
            et_enter_salary1.visibility=View.GONE
            iv_dropdown1.visibility=View.GONE
            tv_remove.visibility=View.INVISIBLE
            add_another.visibility=View.VISIBLE
           tv_expected_salary1.visibility=View.INVISIBLE
            et_expected_salary1.visibility=View.INVISIBLE
            tv_prefered_location_no.visibility=View.INVISIBLE
            tv_location.visibility=View.VISIBLE
            tv_prefered_location.visibility=View.VISIBLE
            iv_dropdown_location.visibility=View.VISIBLE
            tv_next_yes.visibility=View.VISIBLE

            tv_job_category.visibility=View.INVISIBLE
            tv_spinner_no.visibility=View.INVISIBLE
            iv_dropdown_no.visibility=View.INVISIBLE
            tv_location_no.visibility=View.INVISIBLE
            iv_dropdown_location_no.visibility=View.INVISIBLE
            tv_next_no.visibility=View.INVISIBLE
        }

        tv_next_yes.setOnClickListener {
//            array.add(salary)
//            val m= et_enter_salary1.text.toString().toInt()
//           val k = tv_spinner.selectedItemPosition
//            Log.d("spinner",k.toString())
//            val data=UserDetails(k,isWorkExperience!!,tv_spinner1.selectedItemPosition,m,tv_location.selectedItemPosition,tv_spinner_no.selectedItemPosition,salary)



            findNavController().navigate(R.id.action_createAccountWorkFragment_to_workYouDoFragment)



//            val workExperienceDataHashmap=HashMap<String,Any>()
//            workExperienceDataHashmap["education"]=k
//            workExperienceDataHashmap["isPreWorkExp"]=isWorkExperience!!
//            workExperienceDataHashmap["typeOfPreWorkExp"]=tv_spinner1.selectedItemPosition
//            workExperienceDataHashmap["previousSalary"]= et_enter_salary.text.toString()
//            workExperienceDataHashmap["preferredLocation"]= tv_location.selectedItemPosition
//            workExperienceDataHashmap["jobCategory"]=tv_spinner_no.selectedItemPosition
//            workExperienceDataHashmap["expectedSalary"]=et_expected_salary1.text.toString()



        //            var bundle=Bundle()
//            bundle.putParcelable("Key",data)







//            bundle.putParcelable("key",workExperienceDataHashmap.toString())
            //Log.d("workExperience",workExperienceDataHashmap.toString())

           // findNavController().navigate(R.id.action_createAccountWorkFragment_to_workYouDoFragment,bundle)
        }
        tv_next_no.setOnClickListener {
//            array.add(salary)
//            val m= et_enter_salary1.text.toString().toInt()
//            val data=UserDetails(tv_spinner.selectedItemPosition,isWorkExperience!!,tv_spinner1.selectedItemPosition,m,tv_location.selectedItemPosition,tv_spinner_no.selectedItemPosition,salary)

            findNavController().navigate(R.id.action_createAccountWorkFragment_to_workYouDoFragment)



//            val workExperienceDataHashmap=HashMap<String,Any>()
//            val workExperienceDataHashmap=HashMap<String,Any>()
//            workExperienceDataHashmap["education"]=tv_spinner.selectedItemPosition
//            workExperienceDataHashmap["isPreWorkExp"]=isWorkExperience!!
////            workExperienceDataHashmap["typeOfPreWorkExp"]=tv_spinner1.selectedItemPosition
//            workExperienceDataHashmap["previousSalary"]= et_enter_salary.text.toString()
//            workExperienceDataHashmap["preferredLocation"]= tv_location_no.selectedItemPosition
//            workExperienceDataHashmap["jobCategory"]=tv_spinner_no.selectedItemPosition
//            workExperienceDataHashmap["expectedSalary"]=et_expected_salary1.text.toString()
//            val wrap = ParcelableMap<Int,String>(workExperienceDataHashmap)



//
//            var bundle=Bundle()
//            bundle.putParcelable("Key",data)



//            val array=ArrayList<HashMap<String,Any>>()
//            array.add(workExperienceDataHashmap)
//            bundle.putArray("HashMap", workExperienceDataHashmap.toString())


//           bundle.putParcelable("key",workExperienceDataHashmap.toString())
         //   Log.d("workExperience",bundle.toString())


//            findNavController().navigate(R.id.action_createAccountWorkFragment_to_workYouDoFragment,bundle)
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}


