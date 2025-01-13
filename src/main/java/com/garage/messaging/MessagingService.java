package com.garage.messaging;


import com.garage.command.CommandProcessor;
import com.garage.command.ReduceItemQuantityCommand;
import com.garage.command.ReduceItemQuantityProcessor;
import com.garage.inventory.InventoryItem;
import com.garage.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@Slf4j
public class MessagingService {

    @Value(value = "${messaging.kafka.inventoryTopic}")
    private String inventoryTopic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ReduceItemQuantityProcessor reduceItemQuantityProcessor;

    private Map<Object,CommandProcessor> commandProcessorMap;

    public void init() {
        commandProcessorMap.put(ReduceItemQuantityCommand.class,reduceItemQuantityProcessor);
    }

    @KafkaListener(topics = "inventoryTopic", groupId = "inventoryGroup")
    public void listenItemInventoryTopic(ReduceItemQuantityCommand reduceItemQuantityCommand) {
        log.info("Received Message : " + reduceItemQuantityCommand);

        // Process command
        CommandProcessor commandProcessor = commandProcessorMap.get(ReduceItemQuantityCommand.class);
        commandProcessor.processCommand(reduceItemQuantityCommand);
    }

    public void placeOrder(Order order){
        // Code to send OrderItemCommand over kafka
        sendToKafka(Topics.ITEM_ORDER_TOPIC, order.getItem().getId(), order);
    }

    public void reduceItemInventory(InventoryItem inventoryItem){
        // Code to send ReduceItemQuantityCommand over kafka topic
        ReduceItemQuantityCommand reduceItemQuantityCommand = new ReduceItemQuantityCommand();
        reduceItemQuantityCommand.setItemId(inventoryItem.getItem().getId());
        reduceItemQuantityCommand.setQuantity(inventoryItem.getQuantity());

        // TODO: Send to kafka topic with ItemId as key (so that requests for one item and processed sequentially)
        sendToKafka(Topics.ITEM_INVENTORY_TOPIC, inventoryItem.getItem().getId(), reduceItemQuantityCommand);
    }

    private void sendToKafka(Topics topics, String key, Object payload){
        // kafka template code
        kafkaTemplate.send(topics.toString(), key, payload);
    }
}
