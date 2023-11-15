package com.kantarix.device_service.api.dto

data class Device(
    val id: Int,
    val name: String,
    val category: String,
    val capabilities: List<Capability>,
)