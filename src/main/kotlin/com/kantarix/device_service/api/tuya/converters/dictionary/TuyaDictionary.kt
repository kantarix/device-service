package com.kantarix.device_service.api.tuya.converters.dictionary

import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.enums.DeviceCategory
import com.kantarix.device_service.api.enums.WorkMode
import com.kantarix.device_service.api.util.BRIGHTNESS_TUYA
import com.kantarix.device_service.api.util.BRIGHTNESS_TUYA_COMMAND
import com.kantarix.device_service.api.util.COLOR_MODE_TUYA
import com.kantarix.device_service.api.util.COLOR_TUYA
import com.kantarix.device_service.api.util.COLOR_TUYA_COMMAND
import com.kantarix.device_service.api.util.LIGHT_CATEGORY_TUYA
import com.kantarix.device_service.api.util.SWITCH_LED_TUYA
import com.kantarix.device_service.api.util.SWITCH_LED_TUYA_COMMAND
import com.kantarix.device_service.api.util.TEMPERATURE_TUYA
import com.kantarix.device_service.api.util.TEMPERATURE_TUYA_COMMAND
import com.kantarix.device_service.api.util.WHITE_MODE_TUYA
import com.kantarix.device_service.api.util.WORK_MODE_TUYA
import com.kantarix.device_service.api.util.WORK_MODE_TUYA_COMMAND

private val tuyaDictCapabilityCodeReq: Map<CapabilityCode, String> =
    mapOf(
        CapabilityCode.SWITCH_LED   to SWITCH_LED_TUYA_COMMAND,
        CapabilityCode.TEMPERATURE  to TEMPERATURE_TUYA_COMMAND,
        CapabilityCode.COLOR        to COLOR_TUYA_COMMAND,
        CapabilityCode.BRIGHTNESS   to BRIGHTNESS_TUYA_COMMAND,
        CapabilityCode.WORK_MODE    to WORK_MODE_TUYA_COMMAND,
    )

private val tuyaDictCapabilityCodeRes: Map<String, CapabilityCode> =
    mapOf(
        SWITCH_LED_TUYA     to CapabilityCode.SWITCH_LED,
        TEMPERATURE_TUYA    to CapabilityCode.TEMPERATURE,
        COLOR_TUYA          to CapabilityCode.COLOR,
        BRIGHTNESS_TUYA     to CapabilityCode.BRIGHTNESS,
        WORK_MODE_TUYA      to CapabilityCode.WORK_MODE,
    )

private val tuyaDictWorkModeReq: Map<WorkMode, String> =
    mapOf(
        WorkMode.WHITE  to WHITE_MODE_TUYA,
        WorkMode.COLOR  to COLOR_MODE_TUYA,
    )

private val tuyaDictWorkModeRes: Map<String, WorkMode> =
    mapOf(
        WHITE_MODE_TUYA  to WorkMode.WHITE,
        COLOR_MODE_TUYA  to WorkMode.COLOR,
    )

private val tuyaDictDeviceCategoryRes: Map<String, DeviceCategory> =
    mapOf(
        LIGHT_CATEGORY_TUYA  to DeviceCategory.LIGHT,
    )

val tuyaInfoCodes = tuyaDictCapabilityCodeRes.keys

fun CapabilityCode.toTuyaCode(): String? =
    tuyaDictCapabilityCodeReq[this]

fun String.toCapabilityCode(): CapabilityCode? =
    tuyaDictCapabilityCodeRes[this]

fun WorkMode.toTuyaWorkMode(): String? =
    tuyaDictWorkModeReq[this]

fun String.toWorkMode(): WorkMode? =
    tuyaDictWorkModeRes[this]

fun String.toDeviceCategory(): DeviceCategory? =
    tuyaDictDeviceCategoryRes[this]