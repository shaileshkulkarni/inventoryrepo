package com.garage.order;

import com.garage.messaging.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private MessagingService messagingService;

    public boolean orderItem(Order order) {
        // Send Kafka Message to Order Topic
        messagingService.placeOrder(order);
        log.info("Item Ordered : Order : ",order);
        return true;
    }

}
