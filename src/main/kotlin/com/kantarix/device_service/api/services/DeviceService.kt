package com.kantarix.device_service.api.services

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.dto.request.DeviceCapabilitiesRequest
import com.kantarix.device_service.api.dto.request.DeviceRequest
import com.kantarix.device_service.api.dto.request.TuyaDeviceRequest
import com.kantarix.device_service.api.exceptions.ApiError
import com.kantarix.device_service.api.repositories.DeviceRepository
import com.kantarix.device_service.api.tuya.DeviceConnector
import com.kantarix.device_service.api.tuya.converters.dictionary.toDeviceCategory
import com.kantarix.device_service.api.tuya.responses.DeviceResponse
import com.kantarix.device_service.store.entities.DeviceEntity
import com.tuya.connector.api.exceptions.ConnectorResultException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceService(
    private val capabilityService: CapabilityService,
    private val deviceRepository: DeviceRepository,
    private val deviceConnector: DeviceConnector,
) {

    @Transactional(readOnly = true)
    fun getDevices(): List<DeviceSimple> =
        deviceRepository.findAll().map { it.toDeviceSimpleDto() }

    @Transactional(readOnly = true)
    fun getDevice(deviceId: Int) =
        deviceRepository.findDeviceEntityById(deviceId)
        ?.toDeviceDto()
        ?: throw ApiError.DEVICE_NOT_FOUND.toException()

    @Transactional
    fun createDevice(tuyaDevice: TuyaDeviceRequest): Device =
        deviceRepository.findByTuyaId(tuyaDevice.tuyaDeviceId)
            ?.let { throw ApiError.DEVICE_ALREADY_EXIST.toException() }
            ?: try { deviceConnector.getDeviceInfo(tuyaDevice.tuyaDeviceId) }
            catch (e: ConnectorResultException) { throw ApiError.TUYA_DEVICE_NOT_FOUND.toException() }
                .toEntity(name = tuyaDevice.name)
                .also { it.capabilities = capabilityService.createDeviceCapabilities(it.id, it.tuyaId, it) }
                .let { deviceRepository.save(it) }
                .toDeviceDto()

    @Transactional
    fun editDevice(deviceId: Int, device: DeviceRequest): Device =
        deviceRepository.findByIdOrNull(deviceId)
            ?.apply { name = device.name }
            ?.let { deviceRepository.save(it) }
            ?.toDeviceDto()
            ?: throw ApiError.DEVICE_NOT_FOUND.toException()

    @Transactional
    fun editDeviceCapabilities(deviceId: Int, deviceCapabilities: DeviceCapabilitiesRequest) =
        capabilityService.editDeviceCapabilities(deviceId, deviceCapabilities)

    @Transactional
    fun deleteDevice(deviceId: Int) =
        deviceRepository.findByIdOrNull(deviceId)
            ?.let { deviceRepository.deleteById(deviceId) }
            ?: throw ApiError.DEVICE_NOT_FOUND.toException()

    private fun DeviceResponse.toEntity(id: Int = -1, name: String?) =
        DeviceEntity (
            id = id,
            tuyaId = this.id,
            name = name ?: this.name,
            category = this.category.toDeviceCategory() ?: throw IllegalArgumentException(),
        )

}