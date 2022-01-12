package com.example.bigeye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigeye.databinding.ActivityLoginBinding
import com.example.bigeye.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}