package com.example.todomain.common

import io.eventuate.tram.events.common.DomainEvent

class TodoCreated(title: String?, completed: Boolean, executionOrder: Int?) : DomainEvent {
    val title = title
    val completed = completed
    val executionOrder = executionOrder

}
