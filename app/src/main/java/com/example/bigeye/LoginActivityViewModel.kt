package com.example.bigeye

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.RefreshSessionManager
import com.example.bigeye.api.SessionManager
import com.example.bigeye.model.LoginRequest
import com.example.bigeye.model.LoginResponse
import com.example.bigeye.model.UserResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    private lateinit var refreshSessionManager: RefreshSessionManager
    private val context = getApplication<Application>().applicationContext


    var loginLiveData: MutableLiveData<LoginResponse> = MutableLiveData()

    fun getLoginObserver(): MutableLiveData<LoginResponse> {
        return loginLiveData
    }

    fun pushLogin(loginRequest: LoginRequest) {
        apiClient = ApiClient()
        sessionManager = SessionManager(context)
        refreshSessionManager = RefreshSessionManager(context)

        apiClient.getApiService().login(LoginRequest(email = loginRequest.email, password = loginRequest.password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginLiveData.postValue(null)
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    loginLiveData.postValue(response.body())

                    val loginResponse = response.body()

                    if (loginResponse != null) {
                        Log.d("main", response.body().toString())
                        sessionManager.saveAuthToken(loginResponse.accessToken)
                        refreshSessionManager.saveRefreshToken(loginResponse.refreshToken)
                    } else {
                        loginLiveData.postValue(null)

                        val parser = JsonParser()
                        val gson = Gson()
                        var mJson: JsonElement? = parser.parse(response.errorBody()!!.string())
                        val errorResponse: LoginResponse = gson.fromJson(mJson, LoginResponse::class.java)

                        Log.d("main", errorResponse.errorDetails)
                        Toast.makeText(context, errorResponse.errorDetails, Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

}

