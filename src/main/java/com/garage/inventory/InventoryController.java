package com.garage.inventory;

import com.garage.item.Item;
import com.garage.item.ItemDoesNotExistsException;
import com.garage.item.ItemService;
import com.garage.messaging.MessagingService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MessagingService messagingService;

    @PostMapping
    public ResponseEntity<?> itemConsumed(@RequestBody InventoryItem inventoryItem) throws ItemDoesNotExistsException {
        String itemId = inventoryItem.getItem().getId();
        Item dbItem = null;

        if(Strings.isNotBlank(itemId)) {
            dbItem = itemService.getItem(itemId);
        }

        // Validate if the Item Id is valid and exists
        if (dbItem == null){
            return ResponseEntity.badRequest().body(new ItemDoesNotExistsException(itemId));
        }

        // Send message for processing of items inventory reduction command.
        messagingService.reduceItemInventory(inventoryItem);

        return ResponseEntity.ok("");
    }
}
