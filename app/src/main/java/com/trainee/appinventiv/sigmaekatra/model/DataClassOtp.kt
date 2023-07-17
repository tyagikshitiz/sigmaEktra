package com.trainee.appinventiv.sigmaekatra.model

data class DataClassOtp(
    val `data`: DataX,
    val message: String,
    val statusCode: Int,
    val success: Boolean,
    val type: String
)