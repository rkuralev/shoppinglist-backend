package com.kuralev.java.shoppinglist.repository;

import com.kuralev.java.shoppinglist.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> {
}
