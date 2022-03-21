package com.example.bigeye.repository

import com.example.bigeye.api.RetrofitInstance
import com.example.bigeye.model.Account
import com.example.bigeye.model.Login
import com.example.bigeye.model.LoginResponse
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getAccount() : Account {
        return RetrofitInstance.api.getAccount()
    }

    suspend fun pushLogin(login: Login): Response<LoginResponse> {
        return RetrofitInstance.api.pushLogin(login)
    }
}