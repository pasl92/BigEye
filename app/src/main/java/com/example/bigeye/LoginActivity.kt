package com.example.bigeye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
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

    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

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

            viewModel.pushLogin(LoginRequest(email, password))

        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        viewModel.getLoginObserver().observe(this, Observer <LoginResponse> {
            
            if (it == null){
                Toast.makeText(this, viewModel.loginLiveData.value.toString(), Toast.LENGTH_SHORT).show()
            } else{
                val bigEyeActivity = Intent(this, BigEyeActivity::class.java)
                startActivity(bigEyeActivity)
                finishAffinity()
            }
        })
    }
}