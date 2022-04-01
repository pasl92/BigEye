package com.example.bigeye

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bigeye.databinding.ActivityConfirmEmailBinding
import com.example.bigeye.model.Login
import com.example.bigeye.repository.Repository


class ConfirmEmailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityConfirmEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lgButton.setOnClickListener{
            val loginActivity = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginActivity)
        }
    }
}