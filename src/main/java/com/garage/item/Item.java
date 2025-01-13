package com.garage.item;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Entity
@Table(name="items")
@Builder
public class Item {

    public enum SupplierType {
        LOCAL,
        INTERNATIONAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "itemId")
    private String id;

    private String name;

    // Id used by supplier to uniquely identify the item
    private String supplierItemId;

    private SupplierType supplierType;

    private double inventoryThreshold;

    private double minOrderQuantity;
}
