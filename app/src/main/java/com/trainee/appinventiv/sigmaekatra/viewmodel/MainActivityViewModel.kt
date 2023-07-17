package com.trainee.appinventiv.sigmaekatra.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.UserResponse
import com.trainee.appinventiv.sigmaekatra.*
import com.trainee.appinventiv.sigmaekatra.Retrofit.RetroInstance
import com.trainee.appinventiv.sigmaekatra.Retrofit.RetroServiceInterface
import com.trainee.appinventiv.sigmaekatra.model.DataClassOtp
import com.trainee.appinventiv.sigmaekatra.model.VisibilityHandle
import com.trainee.appinventiv.sigmaekatra.model.WorkExperience
import com.trainee.appinventiv.sigmaekatra.repository.OnboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel @Inject constructor(private val onboardRepository: OnboardRepository):ViewModel() {
    val otpVerificationResponse2: LiveData<Response<DataClassOtp>>
        get() = onboardRepository.otpVerifyResponseLiveData

    val cbaMutableLiveData = MutableLiveData<Boolean>()


    val abcMutableLiveData = MutableLiveData<String>()
    val bLiveData : LiveData<String>
        get() = abcMutableLiveData

    var subh =true

    val visi=VisibilityHandle(false)

    fun onEmailTextChanged(s: CharSequence,start: Int,before : Int,count :Int) {
        abcMutableLiveData.value = s.toString()
        if (abcMutableLiveData.value.toString().length>0)

        {
            cbaMutableLiveData.value=true

        }
        else{
            cbaMutableLiveData.value=false
        }

        Log.d("fsgfay534627",abcMutableLiveData.value.toString())
    }



    private val createWorkExperienceMutableLiveData = MutableLiveData<NetworkResult<PicResponse>>()
    val homeLiveData : LiveData<NetworkResult<PicResponse>>
        get() = createWorkExperienceMutableLiveData

    val profileResponseLiveData: LiveData<Response<ProfileResponse>>
        get() = onboardRepository.profileResponseLiveData
    val profilePicResponseLiveData: LiveData<Response<PicResponse>>
    get() = onboardRepository.profilePicResponseLiveData



//    lateinit var createNewUserLiveData: MutableLiveData<Response<DataClassOtp>>
//    init {
//        createNewUserLiveData= MutableLiveData()
//    }

//    val loginResponse : LiveData<Response<DataClassOtp>>
//        get() = loginResponse
//    lateinit var createProfileLiveData: MutableLiveData<Response<DataClassOtp>>
//    init {
//        createNewUserLiveData2= MutableLiveData()
//    }
    /**
     *  verification of otp
     */
    fun verifyotp(deviceid: String,verification: OtpVerification) {
        viewModelScope.launch(Dispatchers.IO) {
            onboardRepository.otpVerification(deviceid,verification)
//        createProfileLiveData.postValue(k)
        }
    }
//
//    fun getCreateNewUserObserver():MutableLiveData<Response<UserResponse>> {
//        return createNewUserLiveData
//    }


    /**
     *  otp verification
     */
    fun createNewUser(user: User) {

        val retroService =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        Log.e("retro object1", retroService.toString())
        val call = retroService.createUser(user)
        Log.e("call1 .....   ", call.toString())
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    Log.e("Tag1", "Success")
                } else
                    Log.e("Tag1", "Failure")
                // createNewUserLiveData.postValue(null)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Tag1", t.message.toString())
                // createNewUserLiveData.postValue(null)
            }

        })
    }
//    fun VerifyOtp(otpVerification: OtpVerification){
//        val retroService =  RetroInstance.getRetroInstance().create(RetroOtpInterface::class.java)
//        Log.e("retro object",retroService.toString())
//        val call = retroService.createUser(otpVerification)
//        Log.e("call2 .....   ",call.toString())
//        call.enqueue(object :Callback<UserResponse>{
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                Log.e("Response otp verify",response.body().toString())
//                if(response.isSuccessful){
//                 //   Log.e("Response otp verify",response.body().toString())
//                    createNewUserLiveData.postValue(response)}
//                else
//                    Log.e("Tag2","Failure")
//                createNewUserLiveData.postValue(null)
//
//
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Log.e("Tag2",t.message.toString())
//                createNewUserLiveData.postValue(null)
//            }
//
//        })
//    }

    fun profileCreate(token: String,deviceid:String, username: createProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            onboardRepository.createProfile(token,deviceid,username)

        }

    }

    fun picUpload(token: String,deviceid:String, profileUrl:picUpload) {
        viewModelScope.launch(Dispatchers.IO) {
            onboardRepository.uploadPic(token, deviceid, profileUrl)
        }
    }
        fun createWork(token: String,deviceid: String,workCreate:WorkExperience){
            viewModelScope.launch(Dispatchers.IO){
                onboardRepository.createWorkExperience(token,deviceid,workCreate).collect {
                    createWorkExperienceMutableLiveData.postValue(it)
                }
            }

        }

    }


