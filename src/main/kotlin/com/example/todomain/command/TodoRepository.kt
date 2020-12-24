package com.example.todomain.command

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.RepositoryDefinition
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : CrudRepository<Todo, Long> {

//    fun abc()
}