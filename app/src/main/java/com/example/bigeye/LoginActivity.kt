package com.example.bigeye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.ApiService
import com.example.bigeye.api.RefreshSessionManager
import com.example.bigeye.api.SessionManager
import com.example.bigeye.databinding.ActivityLoginBinding
import com.example.bigeye.model.LoginRequest
import com.example.bigeye.model.LoginResponse
import com.example.bigeye.model.SignUpResponse
import com.example.bigeye.model.UserResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.math.log


class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var refreshSessionManager: RefreshSessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        refreshSessionManager = RefreshSessionManager(this)



        binding.lgButton.setOnClickListener{
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            if(email.isEmpty()){
                binding.editTextTextEmailAddress.error = "Email required"
                binding.editTextTextEmailAddress.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.editTextTextPassword.error = "Password required"
                binding.editTextTextPassword.requestFocus()
                return@setOnClickListener
            }

            val request = apiClient.getApiService().login(LoginRequest(email = email, password = password))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val loginResponse = response.body()

                        if (loginResponse != null) {
                            Log.d("main", response.body().toString())
                            sessionManager.saveAuthToken(loginResponse.accessToken)
                            refreshSessionManager.saveRefreshToken(loginResponse.refreshToken)
                            val bigEyeActivity = Intent(applicationContext, BigEyeActivity::class.java)
                            startActivity(bigEyeActivity)
                            finishAffinity()
                        } else {
                            val parser = JsonParser()
                            val gson = Gson()

                            var mJson: JsonElement? = parser.parse(response.errorBody()!!.string())
                            val errorResponse: LoginResponse = gson.fromJson(mJson, LoginResponse::class.java)
                            Toast.makeText(this@LoginActivity, errorResponse.errorDetails, Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }
    }
}