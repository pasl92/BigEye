package com.example.bigeye.api

import com.example.bigeye.model.*
import retrofit2.Response
import retrofit2.http.*

interface BigEyeAPI {

    @POST("email-auth/sign-in")
    suspend fun pushLogin(
        @Body login: Login
    ): Response<LoginResponse>

    @POST("email-auth/sign-up")
    suspend fun pushSignUp(
        @Body signUp: SingUp
    ): Response<Unit>

    @GET("account/details")
    suspend fun getAccountDetails(
    ): Response<UserResponse>
}