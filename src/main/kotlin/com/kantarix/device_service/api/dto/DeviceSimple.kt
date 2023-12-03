package com.kantarix.device_service.api.dto

data class DeviceSimple(
    val id: Int,
    val homeId: Int,
    val roomId: Int?,
    val name: String,
    val category: String,
)