package com.kantarix.device_service.api.broker

import com.kantarix.device_service.api.events.HomeDeletedDomainEvent
import com.kantarix.device_service.api.events.RoomDeletedDomainEvent
import com.kantarix.device_service.api.services.DeviceService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class DomainEventListener(
    private val deviceService: DeviceService,
) {

    private val log = KotlinLogging.logger {  }

    @KafkaListener(
        topics = ["HOME_DELETED"],
        containerFactory = "kafkaDomainEventListenerContainerFactory"
    )
    fun listen(event: HomeDeletedDomainEvent) {
        log.info { "Received event: $event" }
        deviceService.deleteDevicesByHomeId(event.homeId)
    }

    @KafkaListener(
        topics = ["ROOM_DELETED"],
        containerFactory = "kafkaDomainEventListenerContainerFactory"
    )
    fun listen(event: RoomDeletedDomainEvent) {
        log.info { "Received event: $event" }
        deviceService.clearRoomsByRoomId(event.roomId)
    }

}