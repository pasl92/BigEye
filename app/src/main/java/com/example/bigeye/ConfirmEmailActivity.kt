package com.example.bigeye

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bigeye.databinding.ActivityConfirmEmailBinding


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