package com.ms.store.producers;

import com.ms.store.dtos.EmailDto;
import com.ms.store.models.StoreModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StoreEmailProducer {
    @Value(value = "${broker.queue.sendPurchase.name}")
    private String routingKey;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishEmailMessage(
            StoreModel storeModel
    ) {
        var emailDto = new EmailDto();
        emailDto.setUserId(storeModel.getUserID());
        emailDto.setEmailTo(storeModel.getUserEmail());
        emailDto.setSubject("Compra finalizada com sucesso!");

        String itens = "";
        for (UUID id : storeModel.getProductsId()) itens = itens + "\n" + id;

        emailDto.setText("ID da sua compra:" + storeModel.getUserID() + "\nForam comprados " + storeModel.getQtd_products() + " itens, conforme identificadores abaixo:\n" + itens+"\n\nObrigado pela preferência! Volte sempre ");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
