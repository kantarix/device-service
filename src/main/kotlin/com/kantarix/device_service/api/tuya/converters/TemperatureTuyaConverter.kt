package com.kantarix.device_service.api.tuya.converters

import com.kantarix.device_service.api.dto.capability.TemperatureCapability
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toCapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toTuyaCode
import com.kantarix.device_service.api.tuya.requests.TuyaCommand
import com.kantarix.device_service.api.tuya.responses.CapabilityResponse
import org.springframework.stereotype.Component

@Component
class TemperatureTuyaConverter : TuyaConverter<TemperatureCapability> {

    override val code = CapabilityCode.TEMPERATURE

    override fun convert(data: TemperatureCapability): TuyaCommand =
        data.run {
            TuyaCommand(
                code = code.toTuyaCode() ?: throw IllegalArgumentException(),
                value = value,
            )
        }

    override fun convert(data: CapabilityResponse): TemperatureCapability =
        data.run {
            TemperatureCapability(
                code = code.toCapabilityCode() ?: throw IllegalArgumentException(),
                value = value as Int,
            )
        }

}