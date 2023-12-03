package com.kantarix.device_service.store.entities

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.enums.DeviceCategory
import com.kantarix.device_service.api.exceptions.ApiError
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "devices")
class DeviceEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    val tuyaId: String,

    val ownerId: Int,

    var homeId: Int,

    var roomId: Int?,

    var name: String,

    @Enumerated(EnumType.STRING)
    val category: DeviceCategory,

    @OneToOne(
        mappedBy = "device",
        targetEntity = CapabilityEntity::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @PrimaryKeyJoinColumn
    var capabilities: CapabilityEntity? = null,

    ) {
    fun toDeviceDto() =
        Device(
            id = id,
            homeId = homeId,
            roomId = roomId,
            name = name,
            category = category.value,
            capabilities = capabilities?.toCapabilityDtoList()
                ?: throw ApiError.CAPABILITIES_NOT_FOUND.toException(),
        )

    fun toDeviceSimpleDto() =
        DeviceSimple(
            id = id,
            homeId = homeId,
            roomId = roomId,
            name = name,
            category = category.value,
        )
}