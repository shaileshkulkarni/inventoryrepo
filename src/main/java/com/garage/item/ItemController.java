package com.garage.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/items")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PutMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        item = itemService.addItem(item);
        return ResponseEntity.ok(item);
    }

}
