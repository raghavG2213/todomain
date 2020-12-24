package com.example.todomain

import com.example.todomain.command.TodoCommandConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@SpringBootApplication
@EnableAutoConfiguration
@Import(TodoCommandConfiguration::class)
@ComponentScan
class TodomainApplication

fun main(args: Array<String>) {
	runApplication<TodomainApplication>(*args)
}
