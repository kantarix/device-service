package com.kantarix.device_service.api.dto

data class ErrorDto(
    val code: String,
    val messages: List<String>,
)