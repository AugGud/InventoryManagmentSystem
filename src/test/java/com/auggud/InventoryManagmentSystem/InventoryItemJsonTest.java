package com.auggud.InventoryManagmentSystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@SpringBootTest
public class InventoryItemJsonTest {

    @Test
    public void testJsonSerializationAndDeserialization() throws Exception {
        // Initialize an instance of InventoryItemTestDTO
        InventoryItemTestDTO originalItem = new InventoryItemTestDTO();
        originalItem.setName("Box");
        originalItem.setDescription("A cardboard box.");
        originalItem.setQuantity(5);
        originalItem.setAmount(new BigDecimal("2.0"));

        // Serialize the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(originalItem);

        // Deserialize the JSON back to an InventoryItemTestDTO object
        InventoryItemTestDTO deserializedItem = objectMapper.readValue(jsonString, InventoryItemTestDTO.class);

        // Assert that the original and deserialized objects are equal
        assertEquals(originalItem.getName(), deserializedItem.getName());
        assertEquals(originalItem.getDescription(), deserializedItem.getDescription());
        assertEquals(originalItem.getQuantity(), deserializedItem.getQuantity());
        assertEquals(originalItem.getAmount(), deserializedItem.getAmount());
    }
}