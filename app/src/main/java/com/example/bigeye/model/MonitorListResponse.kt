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
    val isRunning: Boolean,
    val status: Status
)

data class Status (
    val status: String,
    val averageResponseTime: Int,
    val additionalInfo: String,
)
