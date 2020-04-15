package com.kuralev.java.shoppinglist.dao;

import com.kuralev.java.shoppinglist.model.Item;
import com.kuralev.java.shoppinglist.model.ShoppingList;

import java.util.List;

public interface ShoppingListDao {
    void createList(String uuid, ShoppingList shoppingList);
    ShoppingList readList(String uuid);
    boolean addItem(String listUuid, Item item);
    List<Item> readAllItems(String listUuid);
    boolean updateItem(String listUuid, Item item, int id);
    boolean deleteItem(String listUuid, int itemId);
    Item readItem(String listUuid, int itemId);
}
