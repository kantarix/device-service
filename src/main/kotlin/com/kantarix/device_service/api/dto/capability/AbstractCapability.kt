package com.kantarix.device_service.api.dto.capability

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kantarix.device_service.api.util.BRIGHTNESS
import com.kantarix.device_service.api.util.COLOR
import com.kantarix.device_service.api.util.SWITCH_LED
import com.kantarix.device_service.api.util.TEMPERATURE
import com.kantarix.device_service.api.util.WORK_MODE

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "code",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(
        value = SwitchLedCapability::class,
        name = SWITCH_LED,
    ),
    JsonSubTypes.Type(
        value = BrightnessCapability::class,
        name = BRIGHTNESS,
    ),
    JsonSubTypes.Type(
        value = TemperatureCapability::class,
        name = TEMPERATURE,
    ),
    JsonSubTypes.Type(
        value = ColorCapability::class,
        name = COLOR,
    ),
    JsonSubTypes.Type(
        value = WorkModeCapability::class,
        name = WORK_MODE,
    ),
)
abstract class AbstractCapability(
    val code: CapabilityCode,
) {
    abstract val value: Any
}