package com.ms.user.consumers;

import com.ms.user.dtos.BuyDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserProductConsumer {

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${broker.queue.buy.name}")
    public void listenBuyQueue(
            @Payload BuyDto buyDto
    ) {
        try {
            //System.out.println(buyDto.getProductId() + " " + buyDto.getUserId());
            UserModel user = userService.buyProduct(buyDto);

            System.out.println(
                    user.getUserId() + " " +
                    user.getName() + " " +
                    user.getUserId() + " " +
                    user.getEmail() + " " +
                    user.getPassword());

            for(UUID id:user.getProductsId())System.out.println(id);
        } catch (Exception e) {
            System.out.println("Faio");
        }
    }

}
