package com.kantarix.device_service.api.controllers

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.dto.request.CreateDeviceRequest
import com.kantarix.device_service.api.dto.request.DeviceCapabilitiesRequest
import com.kantarix.device_service.api.dto.request.EditDeviceRequest
import com.kantarix.device_service.api.services.DeviceService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device")
class DeviceController(
    private val deviceService: DeviceService,
) {

    @GetMapping
    fun getDevices(
        @RequestParam("ownerId") ownerId: Int,
    ): List<DeviceSimple> = deviceService.getDevices(ownerId)

    @GetMapping("/{deviceId}")
    fun getDevice(
        @PathVariable("deviceId") deviceId: Int,
    ): Device = deviceService.getDevice(deviceId)

    @PostMapping
    fun createDevice(
        @RequestParam("ownerId") ownerId: Int,
        @Validated @RequestBody deviceRequest: CreateDeviceRequest,
    ): Device = deviceService.createDevice(ownerId, deviceRequest)

    @PutMapping("/{deviceId}")
    fun editDevice(
        @PathVariable("deviceId") deviceId: Int,
        @Validated @RequestBody deviceRequest: EditDeviceRequest,
    ): Device = deviceService.editDevice(deviceId, deviceRequest)

    @PostMapping("/{deviceId}/control")
    fun editDeviceCapabilities(
        @PathVariable("deviceId") deviceId: Int,
        @Validated @RequestBody deviceCapabilities: DeviceCapabilitiesRequest,
    ) = deviceService.editDeviceCapabilities(deviceId, deviceCapabilities)

    @DeleteMapping("/{deviceId}")
    fun deleteDevice(
        @PathVariable("deviceId") deviceId: Int,
    ) = deviceService.deleteDevice(deviceId)

    @GetMapping("/ownership/{deviceId}")
    fun checkOwnership(
        @PathVariable("deviceId") deviceId: Int,
        @RequestParam ownerId: Int,
    ): Boolean = deviceService.checkOwnership(deviceId, ownerId)

}