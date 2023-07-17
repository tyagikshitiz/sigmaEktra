package com.example.example

import com.google.gson.annotations.SerializedName
import com.trainee.appinventiv.sigmaekatra.model.Data


data class UserResponse (

  @SerializedName("statusCode" ) var statusCode : Int?     = null,
  @SerializedName("success"    ) var success    : Boolean? = null,
  @SerializedName("message"    ) var message    : String?  = null,
  @SerializedName("type"       ) var type       : String?  = null,
  @SerializedName("data"       ) var data       : Data?    = Data()

)
//http://44.202.78.173:5008/api-docs/#/