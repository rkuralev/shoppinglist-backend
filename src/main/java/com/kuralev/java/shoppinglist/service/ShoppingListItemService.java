package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.model.Item;

import java.util.List;
import java.util.UUID;

public interface ShoppingListItemService {
    boolean create(UUID listUuid, Item item);
    Item read(UUID listUuid, int itemId);
    List<Item> readAll(UUID listUuid);
    boolean update(UUID listUuid, Item item, int itemId);
    boolean delete(UUID listUuid, int itemId);
}
