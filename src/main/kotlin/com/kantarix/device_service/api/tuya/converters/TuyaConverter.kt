package com.kantarix.device_service.api.tuya.converters

import com.kantarix.device_service.api.dto.capability.AbstractCapability
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.tuya.requests.TuyaCommand
import com.kantarix.device_service.api.tuya.responses.CapabilityResponse

interface TuyaConverter<I: AbstractCapability> {
    val code: CapabilityCode
    fun convert(data: I): TuyaCommand
    fun convert(data: CapabilityResponse): I
}