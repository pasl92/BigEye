package com.example.bigeye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bigeye.databinding.ActivityLoginBinding
import com.example.bigeye.databinding.ActivityMainBinding
import com.example.bigeye.repository.Repository

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider( this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAccount()
        viewModel.myResponse.observe(this, Observer { response ->
            Log.d("Response", response.email)
            Log.d("Response", response.accountId)
        })
    }
}