package com.kantarix.device_service.store.entities

import com.kantarix.device_service.api.dto.Capability
import com.kantarix.device_service.api.dto.capability.CapabilityCode
import com.kantarix.device_service.api.dto.capability.ColorHSV
import com.kantarix.device_service.api.dto.capability.WorkMode
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "capabilities")
class CapabilityEntity(

    @Id
    @Column(name = "device_id")
    var deviceId: Int,

    val switchLed: Boolean,

    @Enumerated(EnumType.STRING)
    val workMode: WorkMode,

    val temperature: Int,

    val brightness: Int,

    val colorHue: Int?,
    val colorSaturation: Int?,
    val colorValue: Int?,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    val device: DeviceEntity,

) {
    fun toCapabilitiesList() =
        listOf(
            toSwitchLedCapability(),
            toWorkModeCapability(),
            toTemperatureCapability(),
            toBrightnessCapability(),
            toColorCapability(),
        )

    fun toSwitchLedCapability() =
        Capability(
            code = CapabilityCode.SWITCH_LED.value,
            value = switchLed,
        )

    fun toWorkModeCapability() =
        Capability(
            code = CapabilityCode.WORK_MODE.value,
            value = workMode,
        )

    fun toTemperatureCapability() =
        Capability(
            code = CapabilityCode.TEMPERATURE.value,
            value = temperature,
        )

    fun toBrightnessCapability() =
        Capability(
            code = CapabilityCode.BRIGHTNESS.value,
            value = brightness,
        )

    fun toColorCapability() =
        Capability(
            code = CapabilityCode.COLOR.value,
            value = ColorHSV(hue = colorHue, saturation = colorSaturation, value = colorValue),
        )

}