package com.garage.command;

import com.garage.inventory.InventoryExhaustedException;
import com.garage.inventory.InventoryService;
import com.garage.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReduceItemQuantityProcessor implements  CommandProcessor<ReduceItemQuantityCommand>{

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Class<ReduceItemQuantityCommand> getCommandType() {
        return ReduceItemQuantityCommand.class;
    }

    @Override
    public void processCommand(ReduceItemQuantityCommand reduceItemQuantityCommand) {
        try {
            Item item = Item.builder().id(reduceItemQuantityCommand.getItemId()).build();
            inventoryService.reduceItemInventory(item, reduceItemQuantityCommand.getQuantity());
        } catch (InventoryExhaustedException e) {
            log.error("",e);
        }
    }
}
