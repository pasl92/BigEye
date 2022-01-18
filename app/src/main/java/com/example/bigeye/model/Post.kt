package com.example.bigeye.model

data class Account(
    val email: String,
    val accountId: String,
    val errorCode: String,
    var errorDetails: String
)