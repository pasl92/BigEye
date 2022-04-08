package com.example.bigeye.model

data class MonitorIdResponse (
    var id : String,
    var name : String,
    var description : String,
    var isRunning : Boolean,
    var status : Status,
    var configuration : Configuration
)

data class Configuration (

    var address : String,
    var port : Int,
    var successResponseCode : Int,
    var timeout : Int,
    var period : Int
)