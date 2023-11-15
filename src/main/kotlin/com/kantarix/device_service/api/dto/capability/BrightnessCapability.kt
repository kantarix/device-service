package com.kantarix.device_service.api.dto.capability

class BrightnessCapability(
    code: CapabilityCode,
    override val value: Int,
) : AbstractCapability(code)