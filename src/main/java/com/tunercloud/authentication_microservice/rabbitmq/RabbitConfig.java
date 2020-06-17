package com.tunercloud.authentication_microservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("tunercloud.direct");
    }

    private static class ReceiverConfig
    {

        @Bean
        public Queue queue1() {
            return new Queue("account.update");
        }

        @Bean
        public Queue queue2() {
            return new Queue("account.delete");
        }

        @Bean
        public Queue queue3() {
            return new Queue("account.add");
        }


        @Bean
        public Binding binding1(DirectExchange exchange,
                                Queue queue1) {
            return BindingBuilder.bind(queue1)
                    .to(exchange)
                    .with("update");
        }

        @Bean
        public Binding binding2(DirectExchange exchange,
                                Queue queue2) {
            return BindingBuilder.bind(queue2)
                    .to(exchange)
                    .with("delete");
        }

        @Bean
        public Binding binding3(DirectExchange exchange,
                                Queue queue3) {
            return BindingBuilder.bind(queue3)
                    .to(exchange)
                    .with("add");
        }

        @Bean
        public RabbitReceiver receiver() {
            return new RabbitReceiver();
        }
    }

    @Bean
    public RabbitSender sender() {
        return new RabbitSender();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
