package com.ms.email.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_STORE")
public class StoreModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID storeId;
    private int qtd_products;
    private Float totalValue;
    private UUID userID;
    private List<UUID> productsId;

    public StoreModel() {
    }

    public UUID getStoreId() {
        return storeId;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    public int getQtd_products() {
        return qtd_products;
    }

    public void setQtd_products(int qtd_products) {
        this.qtd_products = qtd_products;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public List<UUID> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<UUID> productsId) {
        this.productsId = productsId;
    }
}
