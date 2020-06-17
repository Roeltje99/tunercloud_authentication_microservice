package com.tunercloud.authentication_microservice.rabbitmq;

import com.tunercloud.authentication_microservice.models.Account;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    public void sendUpdate(ArtistWrapper account)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"update", account);
        System.out.println(" [x] Sent '" + account.toString() + " to update");
    }

    public void sendDelete(ArtistWrapper account)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"delete", account);
        System.out.println(" [x] Sent '" + account.toString() + "' to delete");
    }

    public void sendAdd(ArtistWrapper account)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"add", account);
        System.out.println(" [x] Sent '" + account.toString() + "' to add");
    }
}
