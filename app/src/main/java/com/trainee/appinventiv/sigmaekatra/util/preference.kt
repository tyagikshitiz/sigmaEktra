package com.trainee.appinventiv.sigmaekatra.util

import android.content.Context
import android.content.SharedPreferences

class Preference(context: Context) {
    private val tokenPreference = "Session"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(tokenPreference,Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    fun savePreference(token : String?)
    {
        editor.apply {
            putString("tokenSession",token)
            apply()
        }
    }
    fun getPreference() : String?
    {
        return sharedPreferences.getString("tokenSession","")
    }

    fun showPage(flag:String)
    {
        editor.apply {
            putString("screen",flag)
            apply()
        }
    }

    fun isAppInstalled() : String?
    {
        return sharedPreferences.getString("screen","")
    }

    fun saveUserType(userType:String){
        editor.apply {
            putString("user",userType)
            apply()
        }
    }

    fun getUserType() : String?{
        return sharedPreferences.getString("user","")
    }
}