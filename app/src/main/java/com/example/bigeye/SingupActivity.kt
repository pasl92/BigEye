package com.example.bigeye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bigeye.databinding.ActivityLoginBinding
import com.example.bigeye.databinding.ActivitySingupBinding
import com.example.bigeye.model.Login
import com.example.bigeye.model.SingUp
import com.example.bigeye.repository.Repository

class SingupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingupBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener{

            val email = binding.emailSignUp.text.toString().trim()
            val password = binding.passwordSignUp.text.toString().trim()

            if(email.isEmpty()){
                binding.emailSignUp.error = "Email required"
                binding.emailSignUp.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.passwordSignUp.error = "Password required"
                binding.passwordSignUp.requestFocus()
                return@setOnClickListener
            }

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

            val mySignUp = SingUp(email, password)
            Log.d("Main", mySignUp.toString())
            viewModel.pushSignUp(mySignUp)
            viewModel.myResponseSignUp.observe(this, Observer{ response ->
                Log.d("Main", response.toString())
                Log.d("Main", response.body().toString())

                if(response.isSuccessful){
                    val confirmEmailActivity = Intent(applicationContext, ConfirmEmailActivity::class.java)
                    startActivity(confirmEmailActivity)
                }else {
                    Toast.makeText(this@SingupActivity, "Wrong User or Password", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}