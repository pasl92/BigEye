package com.example.bigeye

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigeye.model.Account
import com.example.bigeye.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Account> = MutableLiveData()

    fun getAccount() {
        viewModelScope.launch {
            val response:Account = repository.getAccount()
            myResponse.value = response
        }
    }
}