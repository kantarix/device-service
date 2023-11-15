package com.kantarix.device_service.api.dto.capability

import com.kantarix.device_service.api.util.BRIGHTNESS as BRIGHTNESS_VALUE
import com.kantarix.device_service.api.util.COLOR as COLOR_VALUE
import com.kantarix.device_service.api.util.SWITCH_LED as SWITCH_LED_VALUE
import com.kantarix.device_service.api.util.TEMPERATURE as TEMPERATURE_VALUE

enum class CapabilityCode(
    val value: String,
) {
    SWITCH_LED(SWITCH_LED_VALUE),
    TEMPERATURE(TEMPERATURE_VALUE),
    COLOR(COLOR_VALUE),
    BRIGHTNESS(BRIGHTNESS_VALUE);
}