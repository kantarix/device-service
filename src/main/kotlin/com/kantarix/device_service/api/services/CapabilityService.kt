package com.kantarix.device_service.api.services

import com.kantarix.device_service.api.dto.Capability
import com.kantarix.device_service.api.dto.capability.AbstractCapability
import com.kantarix.device_service.api.dto.capability.BrightnessCapability
import com.kantarix.device_service.api.dto.capability.ColorCapability
import com.kantarix.device_service.api.dto.capability.SwitchLedCapability
import com.kantarix.device_service.api.dto.capability.TemperatureCapability
import com.kantarix.device_service.api.dto.capability.WorkModeCapability
import com.kantarix.device_service.api.dto.request.DeviceCapabilitiesRequest
import com.kantarix.device_service.api.exceptions.ApiError
import com.kantarix.device_service.api.repositories.CapabilityRepository
import com.kantarix.device_service.api.tuya.DeviceConnector
import com.kantarix.device_service.api.tuya.converters.TuyaConverter
import com.kantarix.device_service.api.tuya.converters.dictionary.toCapabilityCode
import com.kantarix.device_service.api.tuya.converters.dictionary.tuyaInfoCodes
import com.kantarix.device_service.api.tuya.requests.CommandsRequest
import com.kantarix.device_service.store.entities.CapabilityEntity
import com.kantarix.device_service.store.entities.DeviceEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CapabilityService(
    private val capabilityRepository: CapabilityRepository,
    private val deviceConnector: DeviceConnector,
    tuyaConverters: List<TuyaConverter<*>>,
) {

    private val tuyaConverters = tuyaConverters
        .filterIsInstance<TuyaConverter<AbstractCapability>>()
        .associateBy { it.code }

    fun createDeviceCapabilities(deviceId: Int, tuyaId: String, device: DeviceEntity): CapabilityEntity =
        deviceConnector
            .getDeviceCapabilities(tuyaId, tuyaInfoCodes.joinToString(separator = ","))
            .properties
            .map { tuyaConverters[it.code.toCapabilityCode()]?.convert(it) ?: throw IllegalArgumentException() }
            .let { createCapabilityEntity(deviceId, device).apply { editCapabilities(it) } }

    @Transactional
    fun editDeviceCapabilities(deviceId: Int, deviceCapabilities: DeviceCapabilitiesRequest): List<Capability> =
        capabilityRepository.findByIdOrNull(deviceId)
            ?.apply { editCapabilities(deviceCapabilities.capabilities) }
            ?.also {
                val tuyaId = it.device.tuyaId
                deviceCapabilities.capabilities
                    .map { tuyaConverters[it.code]?.convert(it) ?: throw IllegalArgumentException() }
                    .let { deviceConnector.sendCommand(tuyaId, CommandsRequest(commands = it)) }
            }
            ?.let { capabilityRepository.save(it) }
            ?.toCapabilityDtoList()
            ?: throw ApiError.CAPABILITIES_NOT_FOUND.toException()

    private fun createCapabilityEntity(
        deviceId: Int,
        device: DeviceEntity
    ) =
        CapabilityEntity(
            deviceId = deviceId,
            device = device,
        )

    private fun CapabilityEntity.editCapabilities(
        capabilities: List<AbstractCapability>,
    ) {
        for (capability in capabilities) {
            when (capability) {
                is SwitchLedCapability -> switchLed = capability.value
                is BrightnessCapability -> brightness = capability.value
                is TemperatureCapability -> temperature = capability.value
                is WorkModeCapability -> workMode = capability.value
                is ColorCapability -> {
                    colorHue = capability.value.hue
                    colorSaturation = capability.value.saturation
                    colorValue = capability.value.value
                }
                else -> {}
            }
        }
    }

}