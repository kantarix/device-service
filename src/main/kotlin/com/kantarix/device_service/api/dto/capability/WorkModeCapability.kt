package com.kantarix.device_service.api.dto.capability

import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.enums.WorkMode

class WorkModeCapability(
    code: CapabilityCode,
    override val value: WorkMode,
) : AbstractCapability(code)