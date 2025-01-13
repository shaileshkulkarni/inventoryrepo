package com.garage.inventory;

import com.garage.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<InventoryItem, Item> {

}
