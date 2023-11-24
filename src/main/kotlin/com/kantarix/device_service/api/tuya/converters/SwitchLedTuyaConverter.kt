package com.kantarix.device_service.api.tuya.converters

import com.kantarix.device_service.api.dto.capability.SwitchLedCapability
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toCapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toTuyaCode
import com.kantarix.device_service.api.tuya.requests.TuyaCommand
import com.kantarix.device_service.api.tuya.responses.CapabilityResponse
import org.springframework.stereotype.Component

@Component
class SwitchLedTuyaConverter : TuyaConverter<SwitchLedCapability> {

    override val code = CapabilityCode.SWITCH_LED

    override fun convert(data: SwitchLedCapability): TuyaCommand =
        data.run {
            TuyaCommand(
                code = code.toTuyaCode() ?: throw IllegalArgumentException(),
                value = value,
            )
        }

    override fun convert(data: CapabilityResponse): SwitchLedCapability =
        data.run {
            SwitchLedCapability(
                code = code.toCapabilityCode() ?: throw IllegalArgumentException(),
                value = value as Boolean,
            )
        }

}