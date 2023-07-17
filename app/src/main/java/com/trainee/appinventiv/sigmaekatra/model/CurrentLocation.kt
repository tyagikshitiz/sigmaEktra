package com.trainee.appinventiv.sigmaekatra.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentLocation(val lattitude:String,val longitude:String,val location: String,var category:String)
    : Parcelable

@Parcelize
data class UserDetail(val lattitude:String,val longitude:String,var category:String,val name:String,val dob:String,val email:String)
    :Parcelable
