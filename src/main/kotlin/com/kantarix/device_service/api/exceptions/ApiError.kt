package com.kantarix.device_service.api.exceptions

import org.springframework.http.HttpStatus

enum class ApiError(
    val httpStatus: HttpStatus,
    val message: String,
) {
    DEVICE_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "apiErrors.DEVICE_NOT_FOUND"
    ),
    DEVICE_ALREADY_EXIST(
        HttpStatus.NOT_FOUND,
        "apiErrors.DEVICE_ALREADY_EXIST"
    ),
    CAPABILITIES_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "apiErrors.CAPABILITIES_NOT_FOUND"
    ),
    TUYA_DEVICE_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "apiErrors.TUYA_DEVICE_NOT_FOUND"
    ),
    TUYA_COMMAND_NOT_SUCCEED(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "apiErrors.TUYA_COMMAND_NOT_SUCCEED"
    ),
    ;

    fun toException() =
        ApiException(
            httpStatus = httpStatus,
            code = name,
            message = message,
        )

}