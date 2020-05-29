package com.kuralev.java.shoppinglist.controller;

import com.kuralev.java.shoppinglist.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"schema.sql", "data.sql"})
class ShoppingListItemControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private static String testUuid;

    @BeforeAll
    static void init() {
        testUuid = "fa9eb87a-a0dd-11ea-b6f2-e34907e6fd68";
    }

    @Test
    @DisplayName("Testing GET /lists/{uuid}/items")
    void getAllItemsForTheListTest() {
        ResponseEntity<List<Item>> response = testRestTemplate
                .exchange(
                        String.format("/lists/%s/items", testUuid),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                        );

        assertNotNull(response.getBody());

        Item item = null;
        if (response.getBody().get(0) != null)
            item = response.getBody().get(0);
        else
            fail("Response should contain a List of Items, but it seems that the list is empty");

        assertEquals("list 2 item 1", item.getDescription());
        assertFalse(item.isChecked());
        assertFalse(item.isDeleted());
        assertEquals(1, item.getId());
        assertEquals(0, item.getPriority());
    }

    @Test
    @DisplayName("Testing GET /lists/{uuid}/items/{id}")
    void getItemByIdTest() {
        ResponseEntity<Item> response = testRestTemplate
                .getForEntity(
                        String.format("/lists/%s/items/%d", testUuid, 1),
                        Item.class
                );

        assertNotNull(response.getBody());
        Item item = response.getBody();

        assertFalse(item.isChecked());
        assertFalse(item.isDeleted());
        assertEquals("list 2 item 1", item.getDescription());
        assertEquals(1, item.getId());
        assertEquals(0, item.getPriority());

    }

    @Test
    @DisplayName("Testing POST lists/{uuid}/items")
    void addItemToTheListTest() {

        ResponseEntity<Object> response = testRestTemplate
                .postForEntity(
                        String.format("/lists/%s/items", testUuid),
                        createNewItemRequest(3), //this ID doesn't yet exist in the DB
                        Object.class
                );

        assertEquals(201, response.getStatusCodeValue());
    }



    @Test
    @DisplayName("Testing PUT /lists/{uuid}/items/{id}")
    void updateItemByIdTest() {
        ResponseEntity<Object> response = testRestTemplate
                .exchange(
                        String.format("/lists/%s/items/1", testUuid),
                        HttpMethod.PUT,
                        createNewItemRequest(1), //already existing ID
                        Object.class
                );

        assertEquals(200, response.getStatusCodeValue());

    }

    private HttpEntity<Item> createNewItemRequest(int id) {
        Item item = new Item();
        item.setDeleted(false);
        item.setChecked(false);
        item.setDescription("Facial mask");
        item.setId(id);
        item.setPriority(100);
        return new HttpEntity<>(item);
    }

    @Test
    @DisplayName("Testing DELETE /lists/{uuid}/items/{id}")
    void deleteItemByIdTest() {
        ResponseEntity<Object> response = testRestTemplate
                .exchange(
                        String.format("/lists/%s/items/1", testUuid),
                        HttpMethod.DELETE,
                        createNewItemRequest(1), //already existing ID
                        Object.class
                );

        assertEquals(200, response.getStatusCodeValue());
    }
}