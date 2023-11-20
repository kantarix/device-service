package com.kantarix.device_service.api.dto.capability

import org.hibernate.validator.constraints.Range

class TemperatureCapability(
    code: CapabilityCode,
    @field:Range(min = 0, max = 1000)
    override val value: Int,
) : AbstractCapability(code)