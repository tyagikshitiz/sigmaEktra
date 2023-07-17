package com.trainee.appinventiv.sigmaekatra.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetails(val education:Int,val isPreWorkExp:Boolean,val typeOfPreWorkExp:Int,val previousSalary:Int,
                       val preferredLocation:Int,val jobCategory:Int,val expectedSalary:Int):Parcelable
