package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.model.ShoppingList;
import com.kuralev.java.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public UUID create(ShoppingList shoppingList) {
        UUID uuid = UUID.randomUUID();
        shoppingList.setUuid(uuid);
        shoppingList.getItems().forEach(i -> i.setShoppingList(shoppingList));
        shoppingListRepository.save(shoppingList);
        return uuid;
    }

    @Override
    public ShoppingList read(UUID uuid) {
        ShoppingList result = null;
        if (shoppingListRepository.existsById(uuid))
            result = shoppingListRepository.findById(uuid).get();
        return result;
    }
}
