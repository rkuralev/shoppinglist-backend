package com.kuralev.java.shoppinglist.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {
    @Getter
    private final Map<Integer, Item> listData = new HashMap<>();

    public boolean add(Item item) {
        final int itemId = item.getId();
        if (listData.containsKey(itemId))
            return false;
        else {
            listData.put(itemId, item);
            return true;
        }
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
