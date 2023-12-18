package com.example.ProductProject.controller;

import com.example.ProductProject.model.Item;
import com.example.ProductProject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @PutMapping("/postItem")
    public Item createItem(@RequestBody Item item){
        Item item1 = itemService.createItem(item);
        return item1;
    }

    @GetMapping("/items")
    public List<Item> getItem(){
        return itemService.getItem();
    }

    @PutMapping("/updateItem/{id}")
    public Item updateItemById(@RequestBody Item item, @PathVariable("id") Long id){
        return itemService.updateItem(item, id);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return "Item Deleted";
    }
}
