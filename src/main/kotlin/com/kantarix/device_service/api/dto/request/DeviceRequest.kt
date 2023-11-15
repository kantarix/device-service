package com.kantarix.device_service.api.dto.request

import javax.validation.constraints.NotBlank

data class DeviceRequest (
    @field:NotBlank(message = "Device name {javax.validation.constraints.NotBlank.message}")
    private val _name: String?,
) {
    val name: String
        get() = _name!!
}