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

            val email = binding.emailLogin.text.toString().trim()
            val password = binding.passwordLogin.text.toString().trim()

            if(email.isEmpty()){
                binding.emailLogin.error = "Email required"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.passwordLogin.error = "Password required"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel=ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

            val myLogin = Login(email, password)
            viewModel.pushLogin(myLogin)
            viewModel.myResponse.observe(this,Observer{ response ->

                if(response.isSuccessful){
                    Toast.makeText(this@LoginActivity, "Thanks for login", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this@LoginActivity, "Wrong User or Password", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}