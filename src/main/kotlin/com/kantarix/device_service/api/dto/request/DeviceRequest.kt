package com.kantarix.device_service.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class DeviceRequest (
    @field:NotBlank
    @JsonProperty("name")
    private val _name: String?,
) {
    val name: String
        get() = _name!!
}