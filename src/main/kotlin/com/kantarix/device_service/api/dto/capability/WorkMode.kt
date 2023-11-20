package com.kantarix.device_service.api.dto.capability

import com.kantarix.device_service.api.util.COLOR_MODE as COLOR_MODE_VALUE
import com.kantarix.device_service.api.util.WHITE_MODE as WHITE_MODE_VALUE

enum class WorkMode(
    val value: String,
) {
    WHITE(WHITE_MODE_VALUE),
    COLOR(COLOR_MODE_VALUE);
}