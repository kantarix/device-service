package com.kantarix.device_service.api.dto

import com.kantarix.device_service.api.enums.CapabilityCode

data class Capability(
    val code: CapabilityCode,
    val value: Any,
)