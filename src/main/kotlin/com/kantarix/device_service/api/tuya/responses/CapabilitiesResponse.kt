package com.kantarix.device_service.api.tuya.responses

data class CapabilitiesResponse(
    val properties: List<CapabilityResponse>
)

data class CapabilityResponse(
    val code: String,
    val value: Any,
)