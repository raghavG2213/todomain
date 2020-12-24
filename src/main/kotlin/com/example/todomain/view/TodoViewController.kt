package com.example.todomain.view

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/todos"])
class TodoViewController {

    @Autowired
    private val todoViewService: TodoViewService? = null

    @RequestMapping(method = [RequestMethod.GET])
    fun search(@RequestParam search: String?): List<TodoView?>? {
        return todoViewService?.search(search)
    }
}