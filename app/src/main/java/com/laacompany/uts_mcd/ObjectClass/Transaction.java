package com.laacompany.uts_mcd.ObjectClass;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private String name, description;
    private int quantity;

    public Transaction(String name, String description, int quantity){
        this(UUID.randomUUID(), name, description, quantity);
    }

    public Transaction(UUID uuid, String name, String description, int quantity){
        this.id = uuid;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
