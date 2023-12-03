package com.kantarix.device_service.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class CreateDeviceRequest (
    @field:NotBlank
    @JsonProperty("tuyaDeviceId")
    private val _tuyaDeviceId: String?,

    @field:Length(min = 1)
    val name: String?,

    @JsonProperty("homeId")
    private val _homeId: Int?,

    val roomId: Int?,
) {
    val tuyaDeviceId: String
        get() = _tuyaDeviceId!!

    val homeId: Int
        get() = _homeId!!
}