package com.example.bigeye

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigeye.model.Account
import com.example.bigeye.model.Login
import com.example.bigeye.model.LoginResponse
import com.example.bigeye.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    //val myResponse: MutableLiveData<Account> = MutableLiveData()
    val myResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    //fun getAccount() {
    //    viewModelScope.launch {
    //        val response:Account = repository.getAccount()
    //        myResponse.value = response
    //    }
    //}

    fun pushLogin(login: Login) {
        viewModelScope.launch {
            val response: Response<LoginResponse> = repository.pushLogin(login)
            myResponse.value = response
        }
    }
}