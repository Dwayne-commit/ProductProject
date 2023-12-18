package com.example.ProductProject.service;

import com.example.ProductProject.model.Item;
import com.example.ProductProject.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItem(){
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id){
        return itemRepository.findById(id).get();
    }

    @Override
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

    @Override
    public Item updateItem(Item item, Long id){
        Item item1 = itemRepository.findById(id).get();
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());
        item1.setQty(item.getQty());
        return itemRepository.save(item1);
    }
}
