package com.kuralev.java.shoppinglist.dao;

import com.kuralev.java.shoppinglist.model.Item;
import com.kuralev.java.shoppinglist.model.ShoppingList;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShoppingListDaoImpl implements ShoppingListDao {

    private final static Map<String, ShoppingList> DATA_STORAGE = new HashMap<>();

    @Override
    public void createList(String uuid, ShoppingList shoppingList) {
        DATA_STORAGE.put(uuid, shoppingList);
    }

    @Override
    public ShoppingList readList(String uuid) {
        return DATA_STORAGE.get(uuid);
    }

    @Override
    public void addItem(String listUuid, Item item) {
        DATA_STORAGE.get(listUuid).add(item);
    }

    @Override
    public List<Item> readAllItems(String listUuid) {
        return DATA_STORAGE.get(listUuid).readAll();
    }

    @Override
    public boolean updateItem(String listUuid, Item item, int id) {
        return DATA_STORAGE.get(listUuid).update(item, id);
    }

    @Override
    public boolean deleteItem(String listUuid, int itemId) {
        return DATA_STORAGE.get(listUuid).remove(itemId);
    }

    @Override
    public Item readItem(String listUuid, int itemId) {
        return DATA_STORAGE.get(listUuid).get(itemId);
    }
}
