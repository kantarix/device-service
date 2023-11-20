package com.kantarix.device_service.api.services

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.dto.request.DeviceCapabilitiesRequest
import com.kantarix.device_service.api.dto.request.DeviceRequest
import com.kantarix.device_service.api.dto.request.TuyaDeviceRequest
import com.kantarix.device_service.api.repositories.DeviceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
) {

    @Transactional(readOnly = true)
    fun getDevices(): List<DeviceSimple> =
        TODO()

    @Transactional(readOnly = true)
    fun getDevice(deviceId: Int): Device =
        TODO()

    @Transactional
    fun createDevice(tuyaDevice: TuyaDeviceRequest): Device =
        TODO()

    @Transactional
    fun editDevice(deviceId: Int, device: DeviceRequest): Device =
        TODO()

    @Transactional
    fun editDeviceCapabilities(deviceId: Int, deviceCapabilities: DeviceCapabilitiesRequest) {
        TODO()
    }

    @Transactional
    fun deleteDevice(deviceId: Int): Any {
        TODO()
    }

}