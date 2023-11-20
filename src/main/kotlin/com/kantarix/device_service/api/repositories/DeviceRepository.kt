package com.kantarix.device_service.api.repositories

import com.kantarix.device_service.store.entities.DeviceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceRepository : JpaRepository<DeviceEntity, Int>