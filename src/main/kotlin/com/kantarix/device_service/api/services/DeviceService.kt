package com.kantarix.device_service.api.services

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.dto.request.DeviceCapabilitiesRequest
import com.kantarix.device_service.api.dto.request.EditDeviceRequest
import com.kantarix.device_service.api.dto.request.CreateDeviceRequest
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
    fun getDevices(ownerId: Int): List<DeviceSimple> =
        deviceRepository.findAllByOwnerId(ownerId)
            .map { it.toDeviceSimpleDto() }

    @Transactional(readOnly = true)
    fun getDevice(deviceId: Int) =
        deviceRepository.findDeviceEntityById(deviceId)
        ?.toDeviceDto()
        ?: throw ApiError.DEVICE_NOT_FOUND.toException()

    @Transactional
    fun createDevice(ownerId: Int, deviceRequest: CreateDeviceRequest): Device =
        deviceRepository.findByTuyaId(deviceRequest.tuyaDeviceId)
            ?.let { throw ApiError.DEVICE_ALREADY_EXIST.toException() }
            ?: try { deviceConnector.getDeviceInfo(deviceRequest.tuyaDeviceId) }
            catch (e: ConnectorResultException) { throw ApiError.TUYA_DEVICE_NOT_FOUND.toException() }
                .toEntity(ownerId = ownerId, device = deviceRequest)
                .also { it.capabilities = capabilityService.createDeviceCapabilities(it.id, it.tuyaId, it) }
                .let { deviceRepository.save(it) }
                .toDeviceDto()

    @Transactional
    fun editDevice(deviceId: Int, deviceRequest: EditDeviceRequest): Device =
        deviceRepository.findByIdOrNull(deviceId)
            ?.apply {
                name = deviceRequest.name
                homeId = deviceRequest.homeId ?: this.homeId
                roomId = deviceRequest.roomId
            }
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

    @Transactional(readOnly = true)
    fun checkOwnership(deviceId: Int, ownerId: Int): Boolean =
        deviceRepository.findByIdOrNull(deviceId)
            ?.checkIsAccessAllowed(ownerId)
            ?: throw ApiError.DEVICE_NOT_FOUND.toException()

    fun DeviceEntity.checkIsAccessAllowed(ownerId: Int) =
        this.ownerId == ownerId

    private fun DeviceResponse.toEntity(ownerId: Int, device: CreateDeviceRequest, id: Int = -1) =
        DeviceEntity (
            id = id,
            tuyaId = this.id,
            ownerId = ownerId,
            homeId = device.homeId,
            roomId = device.roomId,
            name = device.name ?: this.name,
            category = this.category.toDeviceCategory() ?: throw IllegalArgumentException(),
        )

}