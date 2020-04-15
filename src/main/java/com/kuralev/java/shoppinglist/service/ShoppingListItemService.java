package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.model.Item;

import java.util.List;

public interface ShoppingListItemService {
    void create(String listUuid, Item item);
    Item read(String listUuid, int itemId);
    List<Item> readAll(String listUuid);
    boolean update(String listUuid, Item item, int itemId);
    boolean delete(String listUuid, int itemId);
}
