package com.kantarix.device_service.store.entities

import com.kantarix.device_service.api.dto.Device
import com.kantarix.device_service.api.dto.DeviceSimple
import com.kantarix.device_service.api.dto.category.Category
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "devices")
class DeviceEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    val name: String,

    val category: Category,

    @OneToMany(mappedBy = "device", targetEntity = CapabilityEntity::class, fetch = FetchType.LAZY)
    var capabilities: List<CapabilityEntity>? = null,

    ) {
    fun toDeviceDto() =
        Device(
            id = id,
            name = name,
            category = category.value,
            capabilities = capabilities?.map { it.toCapabilityDto() } ?: emptyList()
        )

    fun toDeviceSimpleDto() =
        DeviceSimple(
            id = id,
            name = name,
            category = category.value,
        )
}