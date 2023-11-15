package com.kantarix.device_service.api.dto.request

import javax.validation.constraints.NotBlank

data class TuyaDeviceRequest (
    @field:NotBlank(message = "Tuya device id {javax.validation.constraints.NotBlank.message}")
    private val _tuyaDeviceId: String?,

    val name: String?,
) {
    val tuyaDeviceId: String
        get() = _tuyaDeviceId!!
}