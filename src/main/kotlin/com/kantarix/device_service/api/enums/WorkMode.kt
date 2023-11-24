package com.kantarix.device_service.api.enums

import com.kantarix.device_service.api.util.COLOR_MODE
import com.kantarix.device_service.api.util.WHITE_MODE

enum class WorkMode(
    val value: String,
) {
    WHITE(WHITE_MODE),
    COLOR(COLOR_MODE);
}