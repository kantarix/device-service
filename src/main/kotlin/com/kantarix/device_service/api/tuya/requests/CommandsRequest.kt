package com.kantarix.device_service.api.tuya.requests

class CommandsRequest(
    val commands: List<TuyaCommand>
)

data class TuyaCommand(
    val code: String,
    val value: Any,
)

data class ColorTuyaHSV(
    val h: Int,
    val s: Int,
    val v: Int,
)