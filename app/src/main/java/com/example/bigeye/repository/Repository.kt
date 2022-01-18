package com.example.bigeye.repository

import com.example.bigeye.api.RetrofitInstance
import com.example.bigeye.model.Account

class Repository {

    suspend fun getAccount() : Account {
        return RetrofitInstance.api.getAccount()
    }
}