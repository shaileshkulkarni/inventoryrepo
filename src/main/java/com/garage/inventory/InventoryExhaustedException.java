package com.garage.inventory;

public class InventoryExhaustedException extends Throwable {

    private String itemId;

    public InventoryExhaustedException(String itemId){
        this.itemId = itemId;
    }
}
