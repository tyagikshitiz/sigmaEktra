package com.trainee.appinventiv.sigmaekatra.model

data class WorkExperience(
    val education: Int,
    val expectedSalary: Int,
    val isPreWorkExp: Boolean,
    val jobCategory: Int,
    val preferredLocation: Int,
    val previousSalary: List<Int>,
    val typeOfPreWorkExp: Int,
    val workLookingFor: List<String>
)