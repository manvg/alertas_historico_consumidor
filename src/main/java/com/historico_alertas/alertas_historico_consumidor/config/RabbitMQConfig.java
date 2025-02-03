package com.historico_alertas.alertas_historico_consumidor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.alertas}")
    private String alertasExchange;

    @Value("${rabbitmq.queue.alertas_historico}")
    private String alertasHistoricoQueue;

    @Value("${rabbitmq.routingkey.alertas_historico}")
    private String historicoRoutingKey;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(alertasExchange);
    }

    @Bean
    public Queue historicoQueue() {
        return new Queue(alertasHistoricoQueue, true);
    }

    @Bean
    public Binding bindingHistorico(Queue historicoQueue, DirectExchange exchange) {
        return BindingBuilder.bind(historicoQueue).to(exchange).with(historicoRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
