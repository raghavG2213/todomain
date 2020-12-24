package com.example.todomain.common

data class TodoUpdated(private var title: String? = null,


        val completed: Boolean = false,


                val executionOrder: Int = 0) {
}