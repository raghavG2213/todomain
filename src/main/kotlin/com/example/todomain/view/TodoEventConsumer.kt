package com.example.todomain.view

import com.example.todomain.command.Todo
import com.example.todomain.common.TodoCreated
import com.example.todomain.common.TodoUpdated
import io.eventuate.tram.events.subscriber.DomainEventEnvelope
import io.eventuate.tram.events.subscriber.DomainEventHandlers
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder
import org.springframework.beans.factory.annotation.Autowired
import java.util.function.Consumer

class TodoEventConsumer {
    @Autowired
    private val todoViewService: TodoViewService? = null

    fun domainEventHandlers(): DomainEventHandlersBuilder? {
        return DomainEventHandlersBuilder
                .forAggregateType(Todo::class.java.name)
                .onEvent(TodoCreated::class.java) { dee ->
                    val todoCreated: TodoCreated = dee.event
                    todoViewService!!.index(TodoView(dee.aggregateId,
                            todoCreated.title, todoCreated.completed, todoCreated.executionOrder))
                }
//                .onEvent(TodoUpdated::class.java) { dee ->
//                    val todoUpdated: TodoUpdated = dee.getEvent()
//                    todoViewService!!.index(TodoView(dee.getAggregateId(),
//                            todoUpdated.getTitle(), todoUpdated.isCompleted(), todoUpdated.getExecutionOrder()))
//                }
//                .onEvent(TodoDeleted::class.java, Consumer<DomainEventEnvelope<E?>> { dee: DomainEventEnvelope<E?> -> todoViewService!!.remove(dee.getAggregateId()) })
//                .build()
    }
}