package com.example.bigeye.repository

import com.example.bigeye.api.RetrofitInstance
import com.example.bigeye.model.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class Repository {

    suspend fun getAccount() : Account {
        return RetrofitInstance.api.getAccount()
    }

    suspend fun pushLogin(login: Login): Response<LoginResponse> {
        return RetrofitInstance.api.pushLogin(login)
    }

    suspend fun pushSignUp(signUp: SignUp): Response<Unit> {
        return RetrofitInstance.api.pushSignUp(signUp)
    }
}