package com.garage.inventory;

import com.garage.item.Item;
import com.garage.order.Order;
import com.garage.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderService orderService;

    public void reduceItemInventory(Item item, Double reducedByQuantity) throws InventoryExhaustedException {
        InventoryItem inventoryItem = inventoryRepository.findById(item).get();

        if(inventoryItem!=null && inventoryItem.getItem()!=null){
            Item dbItem = inventoryItem.getItem();
            boolean ordered = false;
            double newQuantity = inventoryItem.getQuantity()-reducedByQuantity;

            if(newQuantity<=0)
                throw new InventoryExhaustedException(dbItem.getId());

            // Check if the Item inventory has dropped than threshold and also check if order is already placed or not.
            if(newQuantity<dbItem.getInventoryThreshold() && !inventoryItem.isOrdered()) {
                // Order Item as quantity reduced
                Order order = Order.builder().item(item).orderQuantity(item.getMinOrderQuantity()).build();

                orderService.orderItem(order);

                ordered = true;
                log.info("Item needs to be ordered : Inventory Item ",inventoryItem);
            }

            inventoryItem.setQuantity(newQuantity);
            inventoryItem.setOrdered(ordered);
            inventoryRepository.save(inventoryItem);
        }
    }

    public void addItemInventory(InventoryItem inventoryItem){
        inventoryRepository.save(inventoryItem);

    }

}
