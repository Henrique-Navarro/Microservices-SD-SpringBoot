package com.ms.product.dtos;

import java.io.Serializable;
import java.util.UUID;

public class BuyDto implements Serializable{
    private UUID productId;
    private UUID userId;


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
