package com.kantarix.device_service.api.services

import com.kantarix.device_service.api.repositories.CapabilityRepository
import org.springframework.stereotype.Service

@Service
class CapabilityService(
    private val capabilityRepository: CapabilityRepository,
) {

}