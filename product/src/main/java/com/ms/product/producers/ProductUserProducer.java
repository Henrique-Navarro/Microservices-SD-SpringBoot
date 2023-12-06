package com.ms.product.producers;

import com.ms.product.dtos.BuyDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductUserProducer {

    @Value(value = "${broker.queue.buy.name}")
    private String routingKey;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishProductMessage(
            BuyDto buyDto
    ) {
        rabbitTemplate.convertAndSend("", routingKey, buyDto);
    }
}
