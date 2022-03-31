package com.example.bigeye.repository

import com.example.bigeye.api.RetrofitInstance
import com.example.bigeye.model.*
import retrofit2.Response

class Repository {

    suspend fun pushLogin(login: Login): Response<LoginResponse> {
        return RetrofitInstance.api.pushLogin(login)
    }

    suspend fun pushSignUp(signUp: SingUp): Response<Unit> {
        return RetrofitInstance.api.pushSignUp(signUp)
    }

    suspend fun getAccountDetails(): Response<UserResponse> {
        return RetrofitInstance.api.getAccountDetails()
    }
}