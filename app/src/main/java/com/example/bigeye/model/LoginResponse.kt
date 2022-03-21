package com.example.bigeye.model

data class LoginResponse(val accessToken:String, val refreshToken:String, val errorCode:String, val errorDetails:String)
