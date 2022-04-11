package com.example.bigeye.model

data class AddMonitorRequest(
    var name : String,
    var description : String,
    var configuration : Configuration,
    var autoStart : Boolean
)