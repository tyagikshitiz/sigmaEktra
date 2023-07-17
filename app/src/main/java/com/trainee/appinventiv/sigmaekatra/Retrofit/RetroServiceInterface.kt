package com.trainee.appinventiv.sigmaekatra.Retrofit

import com.example.example.UserResponse
import com.trainee.appinventiv.sigmaekatra.*
import com.trainee.appinventiv.sigmaekatra.model.DataClassOtp
import com.trainee.appinventiv.sigmaekatra.model.WorkExperience
import com.trainee.appinventiv.sigmaekatra.util.Constants.ApiEndpoints.GENERATE_OTP
import com.trainee.appinventiv.sigmaekatra.util.Constants.ApiEndpoints.PROFILE_CREATE
import com.trainee.appinventiv.sigmaekatra.util.Constants.ApiEndpoints.PROFILE_PIC_UPLOAD
import com.trainee.appinventiv.sigmaekatra.util.Constants.ApiEndpoints.USER_LOGIN
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetroServiceInterface {

    @POST(GENERATE_OTP)
    fun createUser(@Body params: User):Call<UserResponse>


    @POST(USER_LOGIN)
   suspend fun verifyOtp( @Header("deviceid")  deviceId :String,
        @Body params: OtpVerification): Response<DataClassOtp>


   //profileCreate
    @PUT(PROFILE_CREATE)
    suspend fun profileCreate(@Header("authorization") token : String,
                              @Header("deviceid")  deviceId :String,
       @Body params: createProfile): Response<ProfileResponse>

@PUT(PROFILE_PIC_UPLOAD)
    suspend fun profilepic(@Header("authorization") token: String,
                           @Header("deviceid") deviceId: String,
    @Body params:picUpload): Response<PicResponse>

  suspend fun workDetail(@Header("authorization") token:String,
                          @Header("deviceId")deviceId: String,
  @Body param:WorkExperience):Response<PicResponse>

}