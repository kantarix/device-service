package com.kantarix.device_service.api.events

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    property = "type",
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY
)
sealed class DomainEvent(
    val type: DomainEventType
)

enum class DomainEventType {
    HOME_DELETED, ROOM_DELETED
}