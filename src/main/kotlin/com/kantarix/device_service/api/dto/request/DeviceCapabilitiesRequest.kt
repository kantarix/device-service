package com.kantarix.device_service.api.dto.request

import com.kantarix.device_service.api.dto.capability.AbstractCapability
import javax.validation.Valid

data class DeviceCapabilitiesRequest (
    @field:Valid
    val capabilities: List<AbstractCapability>
)