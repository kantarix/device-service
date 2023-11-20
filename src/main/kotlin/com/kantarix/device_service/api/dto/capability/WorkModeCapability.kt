package com.kantarix.device_service.api.dto.capability

class WorkModeCapability(
    code: CapabilityCode,
    override val value: WorkMode,
) : AbstractCapability(code)