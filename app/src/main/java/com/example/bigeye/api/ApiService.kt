package com.example.bigeye.api

import com.example.bigeye.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("identity/email-auth/sign-in")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("identity/email-auth/sign-up")
    fun pushSignUp(@Body request: SignUpRequest): Call<Unit>

    @POST("identity/auth/sign-out")
    fun pushSignOut(@Header("Authorization") token: String,
                    @Body request: SignOutRequest): Call<SignOutResponse>

    @GET("identity/account/details")
    fun getAccountDetails(@Header("Authorization") token: String): Call<UserResponse>

    @GET("monitor/list")
    fun getMonitorList(@Header("Authorization") token: String): Call<MonitorListResponse>

    @GET("monitor/{id}")
    fun getMonitorId(@Header("Authorization") token: String,
                     @Path("id") id: String
    ): Call<MonitorIdResponse>

    @DELETE("monitor/{id}")
    fun deleteMonitorId(@Header("Authorization") token: String,
                        @Path("id") id: String
    ): Call<MonidorDeleteResponse>

    @POST("monitor")
    fun postNewMonitor(@Header("Authorization") token: String,
                       @Body request: AddMonitorRequest): Call<AddMonitorResponse>
}