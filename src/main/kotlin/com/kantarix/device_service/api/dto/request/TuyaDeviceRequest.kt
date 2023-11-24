package com.kantarix.device_service.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class TuyaDeviceRequest (
    @field:NotBlank
    @JsonProperty("tuyaDeviceId")
    private val _tuyaDeviceId: String?,

    @field:Length(min = 1)
    val name: String?,
) {
    val tuyaDeviceId: String
        get() = _tuyaDeviceId!!
}