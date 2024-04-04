package com.auggud.InventoryManagmentSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(new BigDecimal("0.5"), inventoryItem.getAmount());
    }
}