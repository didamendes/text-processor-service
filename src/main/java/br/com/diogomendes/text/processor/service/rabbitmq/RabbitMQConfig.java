package br.com.diogomendes.text.processor.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String FANOUT_EXCHANGE_NAME = "post-service.post-service.v1.e";
    public static final String QUEUE_TEXT_PROCESSOR_SERVICE = "text-processor-service.post-processing.v1.q";

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(FANOUT_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue queueTextProcessorService() {
        return QueueBuilder.durable(QUEUE_TEXT_PROCESSOR_SERVICE).build();
    }

    @Bean
    public Binding bindingTextProcessorService() {
        return BindingBuilder.bind(queueTextProcessorService()).to(exchange()).with("novoPost");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
