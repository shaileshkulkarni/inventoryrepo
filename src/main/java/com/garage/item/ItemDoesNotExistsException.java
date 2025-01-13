package com.garage.item;


public class ItemDoesNotExistsException extends Exception {
    private String itemId;

    public ItemDoesNotExistsException(String itemId){
        this.itemId = itemId;
    }
}
