package com.tunercloud.authentication_microservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunercloud.authentication_microservice.models.Account;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitReceiver
{
    /*
    @RabbitListener(queues = "account.update")
    public void receiveUpdate(Message event) throws JsonProcessingException
    {
        System.out.println(event);

        //Object mapping
        ObjectMapper objectMapper = new ObjectMapper();
        ArtistWrapper accountRabbitData = objectMapper.readValue(new String(event.getBody()), ArtistWrapper.class);

        System.out.println(accountRabbitData);
    }

    @RabbitListener(queues = "account.delete")
    public void receiveDelete(Message event) throws JsonProcessingException
    {
        System.out.println(event);

        //Object mapping
        ObjectMapper objectMapper = new ObjectMapper();
        ArtistWrapper accountRabbitData = objectMapper.readValue(new String(event.getBody()), ArtistWrapper.class);

        System.out.println(accountRabbitData);
    }
    @RabbitListener(queues = "account.add")
    public void receiveAdd(Message event) throws JsonProcessingException
    {
        System.out.println(event);

        //Object mapping
        ObjectMapper objectMapper = new ObjectMapper();
        ArtistWrapper accountRabbitData = objectMapper.readValue(new String(event.getBody()), ArtistWrapper.class);

        System.out.println(accountRabbitData);
    }
        */
}
