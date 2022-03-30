package com.example.bigeye.api

import com.example.bigeye.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface BigEyeAPI {

    @FormUrlEncoded
    @GET("account/details")
    suspend fun getAccount(): Account


    @POST("email-auth/sign-in")
    suspend fun pushLogin(
        @Body login: Login
    ): Response<LoginResponse>

    @POST("email-auth/sign-up")
    suspend fun pushSignUp(
        @Body signUp: SignUp
    ): Response<Unit>
}