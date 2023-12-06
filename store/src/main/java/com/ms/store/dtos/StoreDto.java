package com.ms.store.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class StoreDto implements Serializable {
    UUID userID;

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
