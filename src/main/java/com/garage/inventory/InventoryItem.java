package com.garage.inventory;

import com.garage.item.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name="inventory_items")
public class InventoryItem {

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Item item;

    private double quantity;

    private boolean ordered;
}
