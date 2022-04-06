package com.example.bigeye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigeye.databinding.ActivityMainBinding


class
MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.loginButton.setOnClickListener{
                val loginActivity = Intent(applicationContext, LoginActivity::class.java)
                startActivity(loginActivity)
            }

            binding.singUpButton.setOnClickListener{
                val singUpActivity = Intent(applicationContext, SingUpActivity::class.java)
                startActivity(singUpActivity)
            }

        }
}