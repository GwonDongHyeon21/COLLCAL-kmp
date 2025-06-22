package org.collcal.collcal.component

import android.content.Context
import android.content.SharedPreferences

object Token {
    fun getUserToken(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("CollCal", Context.MODE_PRIVATE)
        return sharedPreferences.getString("CollCalToken", "") ?: ""
    }

    fun saveUserToken(context: Context, token: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("CollCal", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("CollCalToken", token)
        editor.apply()
    }

    fun deleteUserToken(context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("CollCal", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("CollCalToken")
        editor.apply()
    }
}