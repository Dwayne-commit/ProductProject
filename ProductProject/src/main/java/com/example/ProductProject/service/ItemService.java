package com.example.ProductProject.service;

import com.example.ProductProject.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    public Item createItem(Item item);

    public List<Item> getItem();

    public Item getItemById(Long id);

    public void deleteItem(Long id);

    public Item updateItem(Item item, Long id);

}
