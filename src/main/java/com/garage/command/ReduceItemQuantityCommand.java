package com.garage.command;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReduceItemQuantityCommand {
    private String itemId;
    private double quantity;
}
