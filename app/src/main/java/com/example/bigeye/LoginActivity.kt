package com.example.bigeye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigeye.databinding.ActivityLoginBinding
import com.example.bigeye.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.backButton.setOnClickListener{
                var backActivity: Intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(backActivity)
            }
    }
}