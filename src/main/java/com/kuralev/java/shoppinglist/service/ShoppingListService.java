package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.model.ShoppingList;

public interface ShoppingListService {
    String create(ShoppingList shoppingList);
    ShoppingList read(String uuid);
}
