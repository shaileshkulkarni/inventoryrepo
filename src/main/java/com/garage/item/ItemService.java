package com.garage.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item){

        item = itemRepository.save(item);

        log.debug("Added Item : ",item);

        return item;
    }

    public Item getItem(String itemId){
        return itemRepository.findById(itemId).orElse(null);
    }
}
