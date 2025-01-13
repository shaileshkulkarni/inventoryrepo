package com.garage.order;

import com.garage.item.Item;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class Order {

    private Item item;

    private double orderQuantity;
}
