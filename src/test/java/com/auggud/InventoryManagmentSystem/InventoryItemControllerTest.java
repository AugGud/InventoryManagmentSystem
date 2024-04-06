package com.auggud.InventoryManagmentSystem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("INSERT INTO INVENTORY_ITEMS(INV_ITEM_ID, NAME, DESCRIPTION, QUANTITY, AMOUNT) VALUES (99, 'Boxes', 'Medium sized card board box', 20, 0.5)");
        jdbcTemplate.execute("INSERT INTO INVENTORY_ITEMS(INV_ITEM_ID, NAME, DESCRIPTION, QUANTITY, AMOUNT) VALUES (100, 'Paper', 'Various sizes of paper', 50, 0.25)");
        jdbcTemplate.execute("INSERT INTO INVENTORY_ITEMS(INV_ITEM_ID, NAME, DESCRIPTION, QUANTITY, AMOUNT) VALUES (101, 'Pens', 'Ballpoint pens', 100, 0.10)");
        jdbcTemplate.execute("INSERT INTO INVENTORY_ITEMS(INV_ITEM_ID, NAME, DESCRIPTION, QUANTITY, AMOUNT) VALUES (102, 'Dry Erase Markers', 'Highlighters and markers', 30, 0.30)");
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM INVENTORY_ITEMS WHERE INV_ITEM_ID IN (99, 100, 101, 102)");
    }

    @Test
    public void testGetInventoryItemById() throws Exception {
        // Perform a GET request to the endpoint that retrieves an inventory item by ID
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory-items/99")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Parse the response body
        String responseBody = result.getResponse().getContentAsString();
        InventoryItem inventoryItem = objectMapper.readValue(responseBody, InventoryItem.class);

        // Assert that the status code is 200
        assertEquals(200, result.getResponse().getStatus());

        // Assert the response body fields
        assertEquals(99L, inventoryItem.getId());
        assertEquals("Boxes", inventoryItem.getName());
        assertEquals("Medium sized card board box", inventoryItem.getDescription());
        assertEquals(20, inventoryItem.getQuantity());
        assertEquals(0, new BigDecimal("0.5").compareTo(inventoryItem.getAmount()));
    }

    @Test
    public void testGetInventoryItemByIdNotFound() throws Exception {
        // Perform a GET request to the endpoint that retrieves an inventory item by ID
        // Use an ID that does not exist in the database
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory-items/9999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        // Assert that the status code is 404
        assertEquals(404, result.getResponse().getStatus());

        // Assert that the response body is empty
        assertEquals("", result.getResponse().getContentAsString());
    }
    
    @Test
    public void testCreateAndGetInventoryItem() throws Exception {
        // Define the inventory item to be created
        InventoryItem newItem = new InventoryItem("New Item", "This is a new item", 10, new BigDecimal("1.0"));

        // Perform a POST request to create the inventory item
        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventory-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newItem)))
                .andExpect(status().isCreated())
                .andReturn();

        // Parse the response body to get the created item's ID
        String responseBody = createResult.getResponse().getContentAsString();
        InventoryItem createdItem = objectMapper.readValue(responseBody, InventoryItem.class);

        // Assert that the status code is 201
        assertEquals(201, createResult.getResponse().getStatus());

        // Assert that the ID is not null
        assertNotNull(createdItem.getId());

        // Perform a GET request to retrieve the created inventory item
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory-items/" + createdItem.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert that the status code is 200
        assertEquals(200, getResult.getResponse().getStatus());

        // Assert the response body fields
        InventoryItem retrievedItem = objectMapper.readValue(getResult.getResponse().getContentAsString(), InventoryItem.class);
        assertEquals(createdItem.getName(), retrievedItem.getName());
        assertEquals(createdItem.getDescription(), retrievedItem.getDescription());
        assertEquals(createdItem.getQuantity(), retrievedItem.getQuantity());
        assertEquals(0, createdItem.getAmount().compareTo(retrievedItem.getAmount()));

        // Perform a DELETE request to delete the created inventory item
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/inventory-items/" + createdItem.getId()))
                .andExpect(status().isNoContent());
    }
    
    @Test
    public void testGetAllInventoryItems() throws Exception {
        // Perform a GET request to the endpoint that retrieves all inventory items
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory-items")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Parse the response body into a list of InventoryItem objects
        String responseBody = result.getResponse().getContentAsString();
        List<InventoryItem> inventoryItems = objectMapper.readValue(responseBody, new TypeReference<List<InventoryItem>>(){});

        // Assert that the status code is 200
        assertEquals(200, result.getResponse().getStatus());

        // Assert that the response body contains the expected number of items
        assertNotNull(inventoryItems);
        assertEquals(4, inventoryItems.size());

        // Assert the properties of each inventory item in their exact order
        assertEquals(99L, inventoryItems.get(0).getId());
        assertEquals("Boxes", inventoryItems.get(0).getName());
        assertEquals("Medium sized card board box", inventoryItems.get(0).getDescription());
        assertEquals(20, inventoryItems.get(0).getQuantity());
        assertEquals(0, new BigDecimal("0.5").compareTo(inventoryItems.get(0).getAmount()));

        assertEquals(100L, inventoryItems.get(1).getId());
        assertEquals("Paper", inventoryItems.get(1).getName());
        assertEquals("Various sizes of paper", inventoryItems.get(1).getDescription());
        assertEquals(50, inventoryItems.get(1).getQuantity());
        assertEquals(0, new BigDecimal("0.25").compareTo(inventoryItems.get(1).getAmount()));

        assertEquals(101L, inventoryItems.get(2).getId());
        assertEquals("Pens", inventoryItems.get(2).getName());
        assertEquals("Ballpoint pens", inventoryItems.get(2).getDescription());
        assertEquals(100, inventoryItems.get(2).getQuantity());
        assertEquals(0, new BigDecimal("0.10").compareTo(inventoryItems.get(2).getAmount()));

        assertEquals(102L, inventoryItems.get(3).getId());
        assertEquals("Dry Erase Markers", inventoryItems.get(3).getName());
        assertEquals("Highlighters and markers", inventoryItems.get(3).getDescription());
        assertEquals(30, inventoryItems.get(3).getQuantity());
        assertEquals(0, new BigDecimal("0.30").compareTo(inventoryItems.get(3).getAmount()));
    }
}