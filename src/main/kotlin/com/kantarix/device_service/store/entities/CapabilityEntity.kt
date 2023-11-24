package com.kantarix.device_service.store.entities

import com.kantarix.device_service.api.dto.Capability
import com.kantarix.device_service.api.dto.capability.ColorHSV
import com.kantarix.device_service.api.enums.CapabilityCode
import com.kantarix.device_service.api.enums.WorkMode
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "capabilities")
class CapabilityEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    var deviceId: Int,

    var switchLed: Boolean = false,

    @Enumerated(EnumType.STRING)
    var workMode: WorkMode = WorkMode.WHITE,

    var temperature: Int = 0,

    var brightness: Int = 10,

    var colorHue: Int? = null,
    var colorSaturation: Int? = null,
    var colorValue: Int? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "device_id")
    val device: DeviceEntity,

    ) {
    fun toCapabilityDtoList() =
        listOf(
            toSwitchLedCapability(),
            toWorkModeCapability(),
            toTemperatureCapability(),
            toBrightnessCapability(),
            toColorCapability(),
        )

    fun toSwitchLedCapability() =
        Capability(
            code = CapabilityCode.SWITCH_LED,
            value = switchLed,
        )

    fun toWorkModeCapability() =
        Capability(
            code = CapabilityCode.WORK_MODE,
            value = workMode,
        )

    fun toTemperatureCapability() =
        Capability(
            code = CapabilityCode.TEMPERATURE,
            value = temperature,
        )

    fun toBrightnessCapability() =
        Capability(
            code = CapabilityCode.BRIGHTNESS,
            value = brightness,
        )

    fun toColorCapability() =
        Capability(
            code = CapabilityCode.COLOR,
            value = ColorHSV(hue = colorHue, saturation = colorSaturation, value = colorValue),
        )

}