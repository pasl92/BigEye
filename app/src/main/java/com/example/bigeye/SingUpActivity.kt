package com.example.bigeye

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bigeye.api.ApiClient
import com.example.bigeye.databinding.ActivitySingupBinding
import com.example.bigeye.model.SignUpRequest
import com.example.bigeye.model.SignUpResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingupBinding
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()

        binding.signUpButton.setOnClickListener{

            val email = binding.emailSignUp.text.toString().trim()
            val password = binding.passwordSignUp.text.toString().trim()

            if(email.isEmpty()){
                binding.emailSignUp.error = "Email required"
                binding.emailSignUp.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.passwordSignUp.error = "Password required"
                binding.passwordSignUp.requestFocus()
                return@setOnClickListener
            }

            apiClient.getApiService().pushSignUp(SignUpRequest(email = email, password = password))
                .enqueue(object :Callback<Unit> {
                    override fun onFailure(call: Call<Unit>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful){
                            val confirmEmailActivity = Intent(applicationContext, ConfirmEmailActivity::class.java)
                            startActivity(confirmEmailActivity)
                        } else {

                            val parser = JsonParser()
                            val gson = Gson()

                            var mJson: JsonElement? = parser.parse(response.errorBody()!!.string())
                            val errorResponse: SignUpResponse = gson.fromJson(mJson, SignUpResponse::class.java)
                            Toast.makeText(this@SingUpActivity, errorResponse.errorDetails, Toast.LENGTH_LONG).show()
                            Log.d("main", errorResponse.errorDetails)
                        }
                    }
                })
        }
    }
}