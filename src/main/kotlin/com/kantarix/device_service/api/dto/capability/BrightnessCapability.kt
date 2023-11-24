package com.kantarix.device_service.api.dto.capability

import com.kantarix.device_service.api.enums.CapabilityCode
import org.hibernate.validator.constraints.Range

class BrightnessCapability(
    code: CapabilityCode,
    @field:Range(min = 10, max = 1000)
    override val value: Int,
) : AbstractCapability(code)