package com.example.bigeye

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.SessionManager
import com.example.bigeye.databinding.ActivityAddMonitorBinding
import com.example.bigeye.model.AddMonitorRequest
import com.example.bigeye.model.AddMonitorResponse
import com.example.bigeye.model.Configuration
import com.example.bigeye.model.MonitorIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMonitorActivity: AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var binding: ActivityAddMonitorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMonitorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        binding.addMonitorButton.setOnClickListener{
            val name = binding.nameInputText.text.toString().trim()
            val description = binding.descriptionInputText.text.toString().trim()
            val address = binding.addressInputText.text.toString().trim()
            val port = binding.portInputText.text.toString().toInt()
            val timeout = binding.timeoutInputText.text.toString().toInt()
            val responseCode = binding.responseCodeInputText.text.toString().toInt()
            val period = binding.periodInputText.text.toString().toInt()

            apiClient.getApiService().postNewMonitor(
                token = "Bearer ${sessionManager.fetchAuthToken()}",
                AddMonitorRequest(name = name, description = description, Configuration(address = address, port = port, timeout = timeout, successResponseCode = responseCode, period = period), autoStart = true)
            )
                .enqueue(object : Callback<AddMonitorResponse> {
                    override fun onFailure(call: Call<AddMonitorResponse>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<AddMonitorResponse>, response: Response<AddMonitorResponse>){
                        finishAffinity()
                        val bigEyeActivity = Intent(applicationContext, BigEyeActivity::class.java)
                        startActivity(bigEyeActivity)
                    }
                })
        }
    }
}