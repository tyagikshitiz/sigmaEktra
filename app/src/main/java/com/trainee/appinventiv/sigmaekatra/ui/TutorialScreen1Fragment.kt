package com.trainee.appinventiv.sigmaekatra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.databinding.FragmentTutorialScreen1Binding
import kotlinx.android.synthetic.main.fragment_tutorial_screen1.*

class TutorialScreen1Fragment : Fragment() {
lateinit var binding:FragmentTutorialScreen1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var arr1 = arrayOf(R.mipmap.ic_tut1,R.mipmap.ic_tut2,R.mipmap.ic_tut3)
    var i = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_dot1.isSelected=true
        btn_started.setOnClickListener {
            findNavController().navigate(R.id.action_tutorialScreen1Fragment2_to_welcomeFragment)
       //     findNavController().navigate(R.id.action_tutorialScreen1Fragment2_to_creatingAccountStep1)
        }
        tv_skip.setOnClickListener {
            findNavController().navigate(R.id.action_tutorialScreen1Fragment2_to_welcomeFragment)
    //        findNavController().navigate(R.id.action_tutorialScreen1Fragment2_to_creatingAccountStep1)
        }
        iv_next.setOnClickListener {
            i += 1

            tut1.setImageResource(arr1[i])
            when(i){
                0->{
                    iv_dot1.isSelected=true
                    iv_dot2.isSelected=false
                    iv_dot3.isSelected=false
                    tv_skip.isVisible=true
                    iv_next.isVisible=true
                    btn_started.isVisible=false

                }
                1->{
                    iv_dot1.isSelected=false
                    iv_dot2.isSelected=true
                    iv_dot3.isSelected=false
                    tv_skip.isVisible=true
                    iv_next.isVisible=true
                    btn_started.isVisible=false

                }
                2->{
                    iv_dot1.isSelected=false
                    iv_dot2.isSelected=false
                    iv_dot3.isSelected=true
                    tv_skip.isVisible=false
                    iv_next.isVisible=false
                    btn_started.isVisible=true
                }


            }
        }
        container.setOnTouchListener(
         object: OnSwipeTouchListener(requireContext()){

                override fun onSwipeLeft() {
                    super.onSwipeLeft()

                    i += 1
                    tut1.setImageResource(arr1[i])
                    when(i){
                        0->{
                            iv_dot1.isSelected=true
                            iv_dot2.isSelected=false
                            iv_dot3.isSelected=false
                            tv_skip.isVisible=true
                            iv_next.isVisible=true
                            btn_started.isVisible=false

                        }
                        1->{
                            iv_dot1.isSelected=false
                            iv_dot2.isSelected=true
                            iv_dot3.isSelected=false
                            tv_skip.isVisible=true
                            iv_next.isVisible=true
                            btn_started.isVisible=false

                        }
                        2->{
                            iv_dot1.isSelected=false
                            iv_dot2.isSelected=false
                            iv_dot3.isSelected=true
                            tv_skip.isVisible=false
                            iv_next.isVisible=false
                            btn_started.isVisible=true
                        }


                    }
                }


                override fun onSwipeRight() {
                    super.onSwipeRight()

                    i -= 1
                tut1.setImageResource(arr1[i])
                    when(i){
                        0->{
                            iv_dot1.isSelected=true
                            iv_dot2.isSelected=false
                            iv_dot3.isSelected=false
                            tv_skip.isVisible=true
                            iv_next.isVisible=true
                            btn_started.isVisible=false
                        }
                        1->{
                            iv_dot1.isSelected=false
                            iv_dot2.isSelected=true
                            iv_dot3.isSelected=false
                            tv_skip.isVisible=true
                            iv_next.isVisible=true
                            btn_started.isVisible=false

                        }
                        2->{
                            iv_dot1.isSelected=false
                            iv_dot2.isSelected=false
                            iv_dot3.isSelected=true
                            tv_skip.isVisible=false
                            iv_next.isVisible=false
                            btn_started.isVisible=true
                        }


                    }

                }

            }
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_screen1, container, false)
    }


}
