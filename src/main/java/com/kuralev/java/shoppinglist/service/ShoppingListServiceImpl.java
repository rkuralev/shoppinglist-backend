package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.dao.ShoppingListDao;
import com.kuralev.java.shoppinglist.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    final private ShoppingListDao dao;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListDao dao) {
        this.dao = dao;
    }

    @Override
    public String create(ShoppingList shoppingList) {
        String uuid = "uuid34087398734"; //TODO generate random uuid using UUID class
        dao.createList(uuid, new ShoppingList());
        return uuid;
    }

    @Override
    public ShoppingList read(String uuid) {
        return dao.readList(uuid);
    }
}
