package com.kantarix.device_service.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class EditDeviceRequest (
    @field:NotBlank
    @JsonProperty("name")
    private val _name: String?,

    @JsonProperty("homeId")
    private val _homeId: Int?,

    val roomId: Int?,
) {
    val name: String
        get() = _name!!

    val homeId: Int
        get() = _homeId!!
}