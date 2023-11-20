package com.kantarix.device_service.api.dto.capability

import org.hibernate.validator.constraints.Range
import javax.validation.Valid

class ColorCapability(
    code: CapabilityCode,
    @field:Valid
    override val value: ColorHSV,
) : AbstractCapability(code)

class ColorHSV(
    @field:Range(min = 0, max = 360)
    val hue: Int?,
    @field:Range(min = 0, max = 1000)
    val saturation: Int?,
    @field:Range(min = 0, max = 1000)
    val value: Int?,
)