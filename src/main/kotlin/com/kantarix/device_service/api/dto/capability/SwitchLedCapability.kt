package com.kantarix.device_service.api.dto.capability

import com.kantarix.device_service.api.enums.CapabilityCode

class SwitchLedCapability(
    code: CapabilityCode,
    override val value: Boolean,
) : AbstractCapability(code)