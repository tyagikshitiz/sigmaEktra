package com.trainee.appinventiv.sigmaekatra.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trainee.appinventiv.sigmaekatra.*
import com.trainee.appinventiv.sigmaekatra.MainActivity.Companion.deviceId
import com.trainee.appinventiv.sigmaekatra.Retrofit.RetroServiceInterface
import com.trainee.appinventiv.sigmaekatra.model.DataClassOtp
import com.trainee.appinventiv.sigmaekatra.model.UserDetail
import com.trainee.appinventiv.sigmaekatra.model.WorkExperience
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class OnboardRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface) {

    private var  otpVerifyResponseMutableLiveData = MutableLiveData<Response<DataClassOtp>>()
    val otpVerifyResponseLiveData :LiveData<Response<DataClassOtp>>
        get() =  otpVerifyResponseMutableLiveData


//    private var eMutableLiveData = MutableLiveData<Response<UserResponse>>()
//    val otpVerifyResponseLiveData :LiveData<Response<UserResponse>>
//        get() =  otpVerifyResponseMutableLiveData



    private var  profileResponseMutableLiveData = MutableLiveData<Response<ProfileResponse>>()
    val profileResponseLiveData :LiveData<Response<ProfileResponse>>
        get() =  profileResponseMutableLiveData

    private var  profilePicResponseMutableLiveData = MutableLiveData<Response<PicResponse>>()
    val profilePicResponseLiveData : MutableLiveData<Response<PicResponse>>
        get() =  profilePicResponseMutableLiveData

    suspend fun otpVerification(deviceid: String,otpRequestData: OtpVerification) {

   Log.d("otp",otpRequestData.toString())
        Log.d("logloglog", otpRequestData.toString())
       val response=retroServiceInterface.verifyOtp(deviceid,otpRequestData)
        otpVerifyResponseMutableLiveData.postValue(response)
       // return response
        Log.e("oTP verify","call  $response ............................")
      //  Log.e("oTP verify device id ","call  ${MainActivity.deviceId} ............................")
    }
    suspend fun createProfile(token:String,deviceid:String,username: createProfile) {

        val response = retroServiceInterface.profileCreate(token,deviceid, username)
      profileResponseMutableLiveData.postValue(response)
        Log.e("oTP verify", "call  $response ............................")

    }
    suspend fun uploadPic(token:String,deviceid:String,pic:picUpload) {

        val response = retroServiceInterface.profilepic(token,deviceid, pic)
        profilePicResponseLiveData.postValue(response)
        Log.e("oTP verify", "call  $response ............................")

    }
    fun createWorkExperience(token:String,deviceid: String,detail: WorkExperience): Flow<NetworkResult<PicResponse>> {
        return flow{
            emit(NetworkResult.Loading())
            val response = retroServiceInterface.workDetail(token, deviceid,detail)
            try {

                if (response.isSuccessful) {
                    emit(NetworkResult.Success(response.body()!!))
                } else {
                    emit(NetworkResult.Error(response.errorBody().toString()))
                }
            } catch (e:Exception){
                emit(NetworkResult.Error(e.message.toString()))
            }
        }
    }


}