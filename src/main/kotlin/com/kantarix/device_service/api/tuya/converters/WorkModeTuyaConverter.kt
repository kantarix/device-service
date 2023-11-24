package com.kantarix.device_service.api.tuya.converters

import com.kantarix.device_service.api.dto.capability.WorkModeCapability
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toCapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toTuyaCode
import com.kantarix.device_service.api.tuya.converters.dictionary.toTuyaWorkMode
import com.kantarix.device_service.api.tuya.converters.dictionary.toWorkMode
import com.kantarix.device_service.api.tuya.requests.TuyaCommand
import com.kantarix.device_service.api.tuya.responses.CapabilityResponse
import org.springframework.stereotype.Component

@Component
class WorkModeTuyaConverter : TuyaConverter<WorkModeCapability> {

    override val code = CapabilityCode.WORK_MODE

    override fun convert(data: WorkModeCapability): TuyaCommand =
        data.run {
            TuyaCommand(
                code = code.toTuyaCode() ?: throw IllegalArgumentException(),
                value = value.toTuyaWorkMode() ?: throw IllegalArgumentException(),
            )
        }

    override fun convert(data: CapabilityResponse): WorkModeCapability =
        data.run {
            WorkModeCapability(
                code = code.toCapabilityCode() ?: throw IllegalArgumentException(),
                value = (value as String).toWorkMode() ?: throw IllegalArgumentException(),
            )
        }

}