package com.example.bigeye

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigeye.model.*
import com.example.bigeye.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class MainViewModel(private val repository: Repository): ViewModel() {

    //val myResponse: MutableLiveData<Account> = MutableLiveData()
    val myResponseLogin: MutableLiveData<Response<LoginResponse>> = MutableLiveData()
    val myResponseSignUp: MutableLiveData<Response<Unit>> = MutableLiveData()

    //fun getAccount() {
    //    viewModelScope.launch {
    //        val response:Account = repository.getAccount()
    //        myResponse.value = response
    //    }
    //}

    fun pushLogin(login: Login) {
        viewModelScope.launch {
            val response: Response<LoginResponse> = repository.pushLogin(login)
            myResponseLogin.value = response
        }
    }

    fun pushSignUp(signUp: SignUp) {
        viewModelScope.launch {
            val response: Response<Unit> = repository.pushSignUp(signUp)
            myResponseSignUp.value = response
        }
    }
}