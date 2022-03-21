package com.example.bigeye.api

import com.example.bigeye.model.Account
import com.example.bigeye.model.Login
import com.example.bigeye.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BigEyeAPI {

    @FormUrlEncoded
    @GET("account/details")
    suspend fun getAccount(): Account


    @POST("email-auth/sign-in")
    suspend fun pushLogin(
        @Body login: Login
    ): Response<LoginResponse>
}