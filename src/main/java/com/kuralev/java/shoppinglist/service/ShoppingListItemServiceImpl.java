package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.dao.ShoppingListDao;
import com.kuralev.java.shoppinglist.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListItemServiceImpl implements ShoppingListItemService {

    final private ShoppingListDao dao;

    @Autowired
    public ShoppingListItemServiceImpl(ShoppingListDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(String listUuid, Item item) {
        dao.addItem(listUuid, item);
    }

    @Override
    public Item read(String listUuid, int itemId) {
        return dao.readItem(listUuid, itemId);
    }

    @Override
    public List<Item> readAll(String listUuid) {
        return dao.readAllItems(listUuid);
    }

    @Override
    public boolean update(String listUuid, Item item, int itemId) {
        return dao.updateItem(listUuid, item, itemId);
    }

    @Override
    public boolean delete(String listUuid, int itemId) {
        return dao.deleteItem(listUuid, itemId);
    }

}
