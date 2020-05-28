package com.kuralev.java.shoppinglist.controller;

import com.kuralev.java.shoppinglist.model.ShoppingList;
import com.kuralev.java.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/lists")
    public ResponseEntity<?> create(@RequestBody ShoppingList shoppingList) {
        UUID uuid = shoppingListService.create(shoppingList);
        return new ResponseEntity<>(uuid.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/lists/{uuid}")
    public ResponseEntity<ShoppingList> read(@PathVariable(name = "uuid") UUID uuid) {
        ShoppingList shoppingList = shoppingListService.read(uuid);

        return shoppingList != null
                ? new ResponseEntity<>(shoppingList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
