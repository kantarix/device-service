package com.kantarix.device_service.api.exceptions

import org.springframework.http.HttpStatus

enum class ApiError(
    val httpStatus: HttpStatus,
    val message: String,
) {
    DEVICE_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "Home does not exist."
    ),
    ;

    fun toException() =
        ApiException(
            httpStatus = httpStatus,
            code = name,
            message = message,
        )

}