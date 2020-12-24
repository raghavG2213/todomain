package com.example.todomain.command

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(path = ["/todos"])
class TodoCommandController(private val todoCommandService: TodoCommandService) {
//    @Autowired
//    lateinit var

    @RequestMapping(method = [RequestMethod.POST])
    fun create(@RequestBody createTodoRequest: CreateTodoRequest): CreateTodoResponse? {
        println(createTodoRequest.order)

        val todo: Todo? = todoCommandService.create(createTodoRequest)
        return CreateTodoResponse(todo?.id)
    }
}
