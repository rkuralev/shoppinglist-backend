package com.kuralev.java.shoppinglist.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingList {
    private final Map<Integer, Item> listData = new HashMap<>();
    private static final AtomicInteger INTERNAL_ITEM_ID = new AtomicInteger();

    public void add(Item item) {
        listData.put(INTERNAL_ITEM_ID.incrementAndGet(), item);
    }

    public boolean update(Item item, int id) {
        if (listData.containsKey(id)) {
            listData.put(id, item);
            return true;
        }
        return false;
    }

    public boolean remove(int itemId) {
        return listData.remove(itemId) != null;
    }

    public Item get(int itemId) {
        return listData.get(itemId);
    }

    public List<Item> readAll() {
        return new ArrayList<>(listData.values());
    }
}
