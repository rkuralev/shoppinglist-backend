package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.model.ShoppingList;

import java.util.UUID;

public interface ShoppingListService {
    UUID create(ShoppingList shoppingList);
    ShoppingList read(UUID uuid);
}
