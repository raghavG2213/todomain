package com.example.todomain.view

class TodoView{
    val INDEX: String? = "todos"

    val TYPE: String? = "todo"

    var id: String? = null

    var title: String? = null

    var completed: Boolean = false

    var executionOrder: Int? = 0

    constructor(){}

    constructor(id: String?, title: String?, completed: Boolean, executionOrder: Int?){
        this.id = id
        this.title = title
        this.completed = completed
        this.executionOrder = executionOrder

    }
}
