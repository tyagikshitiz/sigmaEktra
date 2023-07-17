package com.trainee.appinventiv.sigmaekatra.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.adapter.JobAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    var arr= ArrayList<String>()

   // private lateinit var chipGroup: ChipGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
  chip_all_filters.setOnClickListener {
      openDialog()
  }
        setupChipGroupDynamically()


  rv_jobs.layoutManager=LinearLayoutManager(requireActivity())
        val adapter = JobAdapter(findNavController(),1)
        rv_jobs.adapter=adapter

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun openDialog() {
        val view = View.inflate(requireContext(), R.layout.add_filtters, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        var dialog = builder.create()


        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val te=dialog.findViewById(R.id.tv_contractor) as TextView
        val et=dialog.findViewById(R.id.tv_call_center) as TextView
        val bpo=dialog.findViewById(R.id.tv_bpo) as TextView
        val architect=dialog.findViewById(R.id.tv_architect) as TextView
        val trainee=dialog.findViewById(R.id.tv_trainee) as TextView
        te.setOnClickListener {
            chipGroup.addView(createChip("Contractor"))
           te.setTextColor(Color.parseColor("#21317B"))
            te.setOnTouchListener { view, motionEvent ->
                Log.e("msg", "first")
                true
            }
        }
        et.setOnClickListener {
            chipGroup.addView(createChip("Call Center"))
           et.setTextColor(Color.parseColor("#21317B"))
            et.setOnTouchListener { view, motionEvent ->
                Log.e("msg", "first")
                true
            }
        }
        bpo.setOnClickListener {
            chipGroup.addView(createChip("BPO"))
        bpo.setTextColor(Color.parseColor("#21317B"))
            bpo.setOnTouchListener { view, motionEvent ->
                Log.e("msg", "first")
                true
            }
        }
        architect.setOnClickListener {
            chipGroup.addView(createChip("Architect"))
        architect.setTextColor(Color.parseColor("#21317B"))
            architect.setOnTouchListener { view, motionEvent ->
                Log.e("msg", "first")
                true
            }
        }
        trainee.setOnClickListener {
            chipGroup.addView(createChip("Trainee"))
        trainee.setTextColor(Color.parseColor("#21317B"))
            trainee.setOnTouchListener { view, motionEvent ->
                Log.e("msg", "first")
                true
            }
        }

    }
    private fun setupChipGroupDynamically() {
        val chipGroup = ChipGroup(requireContext())
        chipGroup.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        chipGroup.isSingleSelection = true
        chipGroup.isSingleLine = false
         rootContainer.addView(chipGroup)
    }

    @SuppressLint("ResourceType")
    private fun createChip(label: String): Chip {
        val chip = Chip(requireActivity(), null, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Entry)
        chip.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        //      app:chipBackgroundColor="#21317B"

//        chip_all_filters.setOnClickListener {
//            chip_all_filters.
//        }

//        chip.setOnClickListener {
//            chip.setChipBackgroundColorResource(Color.parseColor("#21317B"))
//        }

        chip.text = label
        chip.isCloseIconVisible = true
//        chip.isChipIconVisible = true
        chip.setChipBackgroundColorResource(R.color.white)
        chip.setCloseIconResource(R.mipmap.ic_chip)
        chip.closeIconSize= 28F
        chip.setCloseIconTintResource(R.color.grey)
        chip.shapeAppearanceModel.withCornerSize(30F)
        chip.setChipStrokeColorResource(R.color.grey)
        chip.chipStrokeWidth=2F

        chip.chipCornerRadius=13F

       // chip.setChipBackgroundColor(getResources().getColorStateList(R.color.Green));
        chip.isCheckable = true
        chip.isClickable = true
        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip)
            Toast.makeText(requireContext(), "${chip.id}", Toast.LENGTH_SHORT).show()
           Toast.makeText(requireContext(), "Chip deleted successfully", Toast.LENGTH_SHORT).show()
        }
        return chip
    }
//    fun chipNotClickable(){
//
//    }






}



