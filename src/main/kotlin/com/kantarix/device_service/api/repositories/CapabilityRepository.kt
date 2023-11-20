package com.kantarix.device_service.api.repositories

import com.kantarix.device_service.store.entities.CapabilityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CapabilityRepository : JpaRepository<CapabilityEntity, Int>