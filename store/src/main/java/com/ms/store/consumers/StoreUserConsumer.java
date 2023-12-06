package com.ms.store.consumers;

import com.ms.store.dtos.StoreDto;
import com.ms.store.models.StoreModel;
import com.ms.store.services.StoreService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StoreUserConsumer {

    @Autowired
    private StoreService storeService;

    @RabbitListener(queues = "${broker.queue.finish.name}")
    public void listenBuyQueue(
            @Payload StoreModel storeModel
    ) {
        try {
            storeService.finish(storeModel);

            System.out.println("\n"+
                    storeModel.getStoreId()+"\n"+
                    storeModel.getUserID()+"\n"+
                    storeModel.getProductsId()+"\n"+
                    storeModel.getQtd_products()+"\n"+
                    storeModel.getTotalValue()+"\n");

        } catch (Exception e) {
            System.out.println("Faio");
        }
    }
}
