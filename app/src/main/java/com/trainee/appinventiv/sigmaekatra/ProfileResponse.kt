package com.trainee.appinventiv.sigmaekatra

data class ProfileResponse(
    val message: String,
    val statusCode: Int,
    val success: Boolean,
    val type: String
)
data class PicResponse(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val type: String
)
