package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.dao.ShoppingListDao;
import com.kuralev.java.shoppinglist.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    final private ShoppingListDao dao;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListDao dao) {
        this.dao = dao;
    }

    @Override
    public String create(ShoppingList shoppingList) {
        String uuid = UUID.randomUUID().toString();
        dao.createList(uuid, shoppingList);
        return uuid;
    }

    @Override
    public ShoppingList read(String uuid) {
        return dao.readList(uuid);
    }
}
