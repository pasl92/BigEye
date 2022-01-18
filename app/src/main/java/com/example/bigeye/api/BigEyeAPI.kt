package com.example.bigeye.api

import com.example.bigeye.model.Account
import retrofit2.http.GET

interface BigEyeAPI {

    @GET("account/details")
    suspend fun getAccount(): Account
}