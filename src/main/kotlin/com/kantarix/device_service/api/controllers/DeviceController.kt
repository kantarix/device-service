package com.kantarix.device_service.api.controllers

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.dto.request.DeviceCapabilitiesRequest
import com.kantarix.device_service.api.dto.request.DeviceRequest
import com.kantarix.device_service.api.dto.request.TuyaDeviceRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/devices")
class DeviceController(
    private val deviceService: DeviceService,
) {

    @GetMapping
    fun getDevices(): List<DeviceSimple> = deviceService.getDevices()

    @GetMapping("/{deviceId}")
    fun getDevice(
        @PathVariable("deviceId") deviceId: Int,
    ): Device = deviceService.getDevice(deviceId)

    @PostMapping
    fun createDevice(
        @Validated @RequestBody tuyaDevice: TuyaDeviceRequest,
    ): Device = deviceService.createDevice(tuyaDevice)

    @PutMapping("/{deviceId}")
    fun editDevice(
        @PathVariable("deviceId") deviceId: Int,
        @Validated @RequestBody device: DeviceRequest,
    ): Device = deviceService.editDevice(deviceId, device)

    @PostMapping("/{deviceId}/control")
    fun editDeviceCapabilities(
        @PathVariable("deviceId") deviceId: Int,
        @Validated @RequestBody deviceCapabilities: DeviceCapabilitiesRequest,
    ) = deviceService.editDeviceCapabilities(deviceId, deviceCapabilities)

    @DeleteMapping("/{deviceId}")
    fun deleteDevice(
        @PathVariable("deviceId") deviceId: Int,
    ) = deviceService.deleteDevice(deviceId)

}