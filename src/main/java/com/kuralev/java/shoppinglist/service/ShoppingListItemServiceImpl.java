package com.kuralev.java.shoppinglist.service;

import com.kuralev.java.shoppinglist.model.Item;
import com.kuralev.java.shoppinglist.model.ShoppingList;
import com.kuralev.java.shoppinglist.repository.ShoppingListItemRepository;
import com.kuralev.java.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListItemServiceImpl implements ShoppingListItemService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;

    @Autowired
    public ShoppingListItemServiceImpl(ShoppingListRepository shoppingListRepository, ShoppingListItemRepository shoppingListItemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListItemRepository = shoppingListItemRepository;
    }

    @Override
    public boolean create(UUID listUuid, Item item) {
        if (shoppingListRepository.existsById(listUuid)) {
            ShoppingList list = shoppingListRepository.findById(listUuid).get();
            item.setShoppingList(list);
            list.getItems().add(item);
            shoppingListRepository.save(list);
            return true;
        }
        return false;
    }

    @Override
    public Item read(UUID listUuid, int itemId) {
        return shoppingListItemRepository.getByShoppingListUuidAndId(listUuid, itemId);
    }

    @Override
    public List<Item> readAll(UUID listUuid) {
        return shoppingListItemRepository.getAllByShoppingListUuid(listUuid);
    }

    @Override
    public boolean update(UUID listUuid, Item updatedItem, int itemId) {
        Item item = read(listUuid, itemId);
        if (item != null) {
            item.setChecked(updatedItem.isChecked());
            item.setDeleted(updatedItem.isDeleted());
            item.setDescription(updatedItem.getDescription());
            item.setPriority(updatedItem.getPriority());
            shoppingListItemRepository.save(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID listUuid, int itemId) {
        Item item = read(listUuid, itemId);
        if (item != null) {
            item.setDeleted(true);
            shoppingListItemRepository.save(item);
            return true;
        }
        return false;
    }
}
