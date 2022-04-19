package com.example.bigeye.api

import android.content.Context
import android.content.SharedPreferences
import com.example.bigeye.MenuFragment
import com.example.bigeye.R

class SessionManager(context: Context) {
    private var prefsAccessToken: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_ACCESS_TOKEN = "user_access_token"
    }

    fun saveAuthToken(authToken: String) {
        val editorToken = prefsAccessToken.edit()
        editorToken.putString(USER_ACCESS_TOKEN, authToken)
        editorToken.apply()
    }

    fun fetchAuthToken(): String? {
        return prefsAccessToken.getString(USER_ACCESS_TOKEN, null)
    }
}