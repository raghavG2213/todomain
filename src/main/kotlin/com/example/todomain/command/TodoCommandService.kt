package com.example.todomain.command

import com.example.todomain.common.TodoCreated
import io.eventuate.tram.events.common.DomainEvent
import io.eventuate.tram.events.publisher.DomainEventPublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class TodoCommandService {
    @Autowired
    private val todoRepository: TodoRepository? = null

    @Autowired
    private val domainEventPublisher: DomainEventPublisher? = null

    fun create(createTodoRequest: CreateTodoRequest): Todo? {
        var todo = Todo(createTodoRequest.id, createTodoRequest.title, createTodoRequest.completed, createTodoRequest.order)
        todo = todoRepository!!.save(todo)
        println("before")
        publishTodoEvent(todo, TodoCreated(todo.title, todo.completed, todo.execution_order))
        println("after")
        return todo
    }

    private fun publishTodoEvent(todo: Todo, vararg domainEvents: DomainEvent) {
        println("in publish event")
        println(domainEventPublisher?.publish(Todo::class.java, todo.id, listOf(*domainEvents)))
//        todoRepository?.abc()
        domainEventPublisher?.publish(Todo::class.java, todo.id, listOf(*domainEvents))
    }

    private fun publishTodoEvent(id: Long, vararg domainEvents: DomainEvent) {
        domainEventPublisher!!.publish(Todo::class.java, id, listOf(*domainEvents))
    }



//    fun update(id: Long, updateTodoRequest: UpdateTodoRequest) {
//        val todo = todoRepository!!.findById(id).orElseThrow { TodoNotFoundException(id) }
//        todo.setTitle(updateTodoRequest.getTitle())
//        todo.setCompleted(updateTodoRequest.isCompleted())
//        todo.setExecutionOrder(updateTodoRequest.getOrder())
//        todoRepository.save(todo)
//        publishTodoEvent(todo, TodoUpdated(todo.getTitle(), todo.isCompleted(), todo.getExecutionOrder()))
//    }
//
//    fun delete(id: Long) {
//        todoRepository!!.deleteById(id)
//        publishTodoEvent(id, TodoDeleted())
//    }
}