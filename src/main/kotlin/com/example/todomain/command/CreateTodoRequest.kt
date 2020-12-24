package com.example.todomain.command

data class CreateTodoRequest(
        val id: Int? = 0,
        val title: String? = null,
        val completed: Boolean = false,
        var order: Int? = 0)
