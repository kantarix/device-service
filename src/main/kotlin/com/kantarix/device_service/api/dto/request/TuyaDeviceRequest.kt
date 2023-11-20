package com.kantarix.device_service.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class TuyaDeviceRequest (
    @field:NotBlank
    @JsonProperty("tuyaDeviceId")
    private val _tuyaDeviceId: String?,

    val name: String?,
) {
    val tuyaDeviceId: String
        get() = _tuyaDeviceId!!
}