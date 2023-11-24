package com.kantarix.device_service.api.tuya.converters

import com.kantarix.device_service.api.dto.capability.ColorCapability
import com.kantarix.device_service.api.dto.capability.ColorHSV
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toCapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toTuyaCode
import com.kantarix.device_service.api.tuya.requests.ColorTuyaHSV
import com.kantarix.device_service.api.tuya.requests.TuyaCommand
import com.kantarix.device_service.api.tuya.responses.CapabilityResponse
import org.springframework.stereotype.Component

@Component
class ColorTuyaConverter : TuyaConverter<ColorCapability> {

    override val code = CapabilityCode.COLOR

    override fun convert(data: ColorCapability): TuyaCommand =
        data.run {
            TuyaCommand(
                code = code.toTuyaCode() ?: throw IllegalArgumentException(),
                value = colorToColorTuya(value),
            )
        }

    override fun convert(data: CapabilityResponse): ColorCapability =
        data.run {
            ColorCapability(
                code = code.toCapabilityCode() ?: throw IllegalArgumentException(),
                value = colorTuyaToColorHSV(value as String),
            )
        }

    private fun colorTuyaToColorHSV(value: String): ColorHSV =
        value.chunked(4)
            .let {
                ColorHSV(
                    hue = it.getOrNull(0)?.toIntOrNull(16),
                    saturation = it.getOrNull(1)?.toIntOrNull(16),
                    value = it.getOrNull(2)?.toIntOrNull(16),
                )
            }

    private fun colorToColorTuya(value: ColorHSV): ColorTuyaHSV =
        ColorTuyaHSV(
            h = value.hue ?: 0,
            s = value.saturation ?: 0,
            v = value.value ?: 0,
        )

}