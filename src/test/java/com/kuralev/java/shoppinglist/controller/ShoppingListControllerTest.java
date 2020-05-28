package com.kuralev.java.shoppinglist.controller;

import com.kuralev.java.shoppinglist.model.Item;
import com.kuralev.java.shoppinglist.model.ShoppingList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingListControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Testing PUT /lists")
    void saveShoppingListTest() {
        Item item1 = new Item();
        item1.setDescription("Bread");
        item1.setId(1);
        item1.setChecked(false);
        item1.setPriority(0);
        item1.setDeleted(false);

        Item item2 = new Item();
        item2.setDescription("Milk");
        item2.setId(2);
        item2.setChecked(true);
        item2.setPriority(10);
        item2.setDeleted(false);

        ArrayList<Item> items = new ArrayList<>(2);
        items.add(item1);
        items.add(item2);

        ShoppingList list = new ShoppingList();
        list.setItems(items);

        HttpEntity<ShoppingList> request = new HttpEntity<>(list);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/lists", request, String.class);

        assertNotNull(response.getBody(), "Response body should not be empty");
        assertDoesNotThrow(() -> UUID.fromString(response.getBody()),
                "Response String should be parsed into UUID without any exceptions");

    }

    @Test
    @DisplayName("Testing GET /lists/{uuid}")
    @Sql({"schema.sql", "data.sql"})
    void getShoppingListTest() {
        assertEquals(3, getListByUuid("952911af-0bbb-45a9-b946-3f4f0a6bff20"),
                "Test List 1 should contain 3 items");
        assertEquals(2, getListByUuid("fa9eb87a-a0dd-11ea-b6f2-e34907e6fd68"),
                "Test List 2 should contain 2 items");
        assertEquals(404, getListByUuid("1dd150e6-a0f7-11ea-93c5-fb2a8d0835b0"),
                "Request with a non-existent UUID should return HTTP code 404");
        assertEquals(400, getListByUuid("not-a-UUID"),
                "Request with a parameter which is not a valid UUID should return HTTP code 400");
    }

    private int getListByUuid(String uuid) {
        ResponseEntity<ShoppingList> response = testRestTemplate
                .getForEntity(String.format("/lists/%s", uuid), ShoppingList.class);

        if (response.getStatusCodeValue() == 200 && response.hasBody())
            if (response.getBody().getItems() != null)
                return response.getBody().getItems().size();

        return response.getStatusCodeValue();
    }
}