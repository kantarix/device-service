package com.kantarix.device_service.api.dto.capability

class ColorCapability(
    code: CapabilityCode,
    override val value: Int,
) : AbstractCapability(code)