package com.kantarix.device_service.store.entities

import com.kantarix.device_service.api.dto.Capability
import com.kantarix.device_service.api.dto.capability.CapabilityCode
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "capabilities")
class CapabilityEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    @Enumerated(EnumType.STRING)
    val code: CapabilityCode,

    val value: Any,

) {
    fun toCapabilityDto() =
        Capability(
            code = code.value,
            value = value,
        )
}