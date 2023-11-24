package com.kantarix.device_service.api.tuya.converters

import com.kantarix.device_service.api.dto.capability.BrightnessCapability
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toCapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toTuyaCode
import com.kantarix.device_service.api.tuya.requests.TuyaCommand
import com.kantarix.device_service.api.tuya.responses.CapabilityResponse
import org.springframework.stereotype.Component

@Component
class BrightnessTuyaConverter : TuyaConverter<BrightnessCapability> {

    override val code = CapabilityCode.BRIGHTNESS

    override fun convert(data: BrightnessCapability): TuyaCommand =
        data.run {
            TuyaCommand(
                code = code.toTuyaCode() ?: throw IllegalArgumentException(),
                value = value,
            )
        }

    override fun convert(data: CapabilityResponse): BrightnessCapability =
        data.run {
            BrightnessCapability(
                code = code.toCapabilityCode() ?: throw IllegalArgumentException(),
                value = value as Int,
            )
        }

}