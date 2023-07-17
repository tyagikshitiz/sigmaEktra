package com.trainee.appinventiv.sigmaekatra

import java.util.*

data class OtpVerification(val phoneNumber:String?,val code: String?)

data class createProfile(val username:String,val dateOfBirth:String,val gender:Int,val emailAddress:String,val locLat:Float,val locLong:Float,val userType:Int)

data class picUpload(val profileUrl: String)

//data class workExperience(val education:Int,val isPreWorkExp:Boolean,val typeOfPreWorkExp:Int,val)
