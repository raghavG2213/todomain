package com.example.todomain.command

import io.eventuate.tram.messaging.common.Message
import io.eventuate.tram.messaging.producer.MessageProducer
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories
@Import(TramEventsPublisherConfiguration::class)

//TramMessageProducerJdbcConfiguration::class, NoopDuplicateMessageDetector::class
class TodoCommandConfiguration {

    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
    private final val twoParameters: (String, Int) -> String = repeatFun // OK

    private final fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }
    val result = runTransformation(twoParameters) // OK

    val msg = ""
    fun send(var1: String?, var2: Message?): (String, Message) -> Unit {
        return ("String", Message) -> Unit
    }

    @Bean
    fun message() = MessageProducer(twoParameters)

//    @Bean
//    fun todoCommandService(): TodoCommandService {
//        return TodoCommandService()
//    }


}