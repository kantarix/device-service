package com.kantarix.device_service.api.dto.capability

class SwitchLedCapability(
    code: CapabilityCode,
    override val value: Boolean,
) : AbstractCapability(code)