package com.example.bigeye.model

data class MonitorListResponse (
    val monitors: List<Monitor>,
    val errorCode: Any? = null,
    val errorDetails: Any? = null
)

data class Monitor (
    val id: String,
    val name: String,
    val description: String,
    val isRunning: Boolean
)