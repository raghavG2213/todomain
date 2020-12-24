package com.example.todomain.command

import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.*

@Entity
@Table(name = "todo")
@Access(AccessType.FIELD)
data class Todo (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = 0,

         val title: String? = "new",

         val completed: Boolean = false,

         val execution_order: Int? = 0
) {



}