package com.example.bigeye.api

import android.content.Context
import android.content.SharedPreferences
import com.example.bigeye.R

class RefreshSessionManager(context: Context) {
    private var prefsRefreshToken: SharedPreferences = context.getSharedPreferences(context.getString(
        R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_REFRESH_TOKEN = "user_refresh_token"
    }

    fun saveRefreshToken(refreshToken: String) {
        val editor = prefsRefreshToken.edit()
        editor.putString(USER_REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun fetchRefreshToken(): String? {
        return prefsRefreshToken.getString(USER_REFRESH_TOKEN, null)
    }
}