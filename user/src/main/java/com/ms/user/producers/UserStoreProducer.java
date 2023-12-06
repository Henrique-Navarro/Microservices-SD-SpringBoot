package com.ms.user.producers;

import com.ms.user.models.StoreModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserStoreProducer {

    @Value(value = "${broker.queue.finish.name}")
    private String routingKey;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishStoreMessage(
            StoreModel storeModel
    ) {
        rabbitTemplate.convertAndSend("", routingKey, storeModel);
    }
}
