package com.ms.user.dtos;

import java.io.Serializable;
import java.util.UUID;

public class BuyDto{
    private UUID productId;
    private UUID userId;

    public BuyDto() {
    }

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
