package com.example.bigeye

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bigeye.databinding.ActivityBigeyeBinding
import com.example.bigeye.model.Login
import com.example.bigeye.repository.Repository


class BigEyeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBigeyeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBigeyeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        viewModel.getAccountDetails()
        viewModel.myResponseUser.observe(this, Observer{ response ->
            Log.d("Main", response.toString())
            Log.d("Main", response.body().toString())

            if(response.isSuccessful){
                Toast.makeText(this@BigEyeActivity, "User ok", Toast.LENGTH_LONG).show()

            }else {
                Toast.makeText(this@BigEyeActivity, "User nie ok", Toast.LENGTH_LONG).show()
            }

        })
    }
}