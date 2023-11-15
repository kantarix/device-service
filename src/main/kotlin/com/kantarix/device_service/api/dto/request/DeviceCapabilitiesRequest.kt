package com.kantarix.device_service.api.dto.request

import com.kantarix.device_service.api.dto.capability.AbstractCapability

data class DeviceCapabilitiesRequest (
    val capabilities: List<AbstractCapability>
)