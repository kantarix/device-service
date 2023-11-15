package com.kantarix.device_service.api.dto.capability

class TemperatureCapability(
    code: CapabilityCode,
    override val value: Int,
) : AbstractCapability(code)