package com.kuralev.java.shoppinglist.controller;

import com.kuralev.java.shoppinglist.model.Item;
import com.kuralev.java.shoppinglist.service.ShoppingListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingListItemController {
    private final ShoppingListItemService shoppingListItemService;

    @Autowired
    public ShoppingListItemController(ShoppingListItemService shoppingListItemService) {
        this.shoppingListItemService = shoppingListItemService;
    }

    @GetMapping("/lists/{uuid}/items")
    public ResponseEntity<List<Item>> readAll(@PathVariable(name = "uuid") String listUuid) {
        List<Item> items = shoppingListItemService.readAll(listUuid);
        return items != null
                ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lists/{uuid}/items/{id}")
    public ResponseEntity<Item> read(@PathVariable(name = "uuid") String listUuid,
                                     @PathVariable(name = "id") int itemId) {
        Item item = shoppingListItemService.read(listUuid, itemId);
        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("lists/{uuid}/items")
    public ResponseEntity<?> create(@PathVariable(name = "uuid") String listUuid,
                                    @RequestBody Item item) {
        boolean created = shoppingListItemService.create(listUuid, item);
        return created
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/lists/{uuid}/items/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "uuid") String listUuid,
                                    @PathVariable(name = "id") int itemId,
                                    @RequestBody Item item) {
        boolean updated = shoppingListItemService.update(listUuid, item, itemId);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/lists/{uuid}/items/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "uuid") String listUuid,
                                    @PathVariable(name = "id") int itemId) {
        boolean deleted = shoppingListItemService.delete(listUuid, itemId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}