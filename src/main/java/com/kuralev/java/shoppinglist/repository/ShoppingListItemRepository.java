package com.kuralev.java.shoppinglist.repository;

import com.kuralev.java.shoppinglist.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShoppingListItemRepository extends JpaRepository<Item, Long> {
    List<Item> getAllByShoppingListUuid(UUID listUuid);
    Item getByShoppingListUuidAndId(UUID listUuid, int id);
}
