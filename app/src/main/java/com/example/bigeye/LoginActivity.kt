package com.example.bigeye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bigeye.databinding.ActivityLoginBinding
import com.example.bigeye.model.Login
import com.example.bigeye.repository.Repository


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel=ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

            val myLogin = Login(email, password)
            viewModel.pushLogin(myLogin)
            viewModel.myResponseLogin.observe(this,Observer{ response ->
                Log.d("Main", response.toString())

                if(response.isSuccessful){
                    Toast.makeText(this@LoginActivity, "Thanks for login", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this@LoginActivity, "Wrong User or Password", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}