package com.auggud.InventoryManagmentSystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class InventoryItemJsonTest {

    @Autowired
    private JacksonTester<InventoryItem> json;

    @Test
    void inventoryItemSerializationTest() throws IOException {
        InventoryItem inventoryItem = new InventoryItem("Box", "Cardboard box", 5, BigDecimal.valueOf(2.0));

        assertThat(json.write(inventoryItem)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(inventoryItem)).hasJsonPathStringValue("@.name");
        assertThat(json.write(inventoryItem)).extractingJsonPathStringValue("@.name")
            .isEqualTo("Box");

        assertThat(json.write(inventoryItem)).hasJsonPathStringValue("@.description");
        assertThat(json.write(inventoryItem)).extractingJsonPathStringValue("@.description")
            .isEqualTo("Cardboard box");

        assertThat(json.write(inventoryItem)).hasJsonPathNumberValue("@.quantity");
        assertThat(json.write(inventoryItem)).extractingJsonPathNumberValue("@.quantity")
            .isEqualTo(5);

        assertThat(json.write(inventoryItem)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(inventoryItem)).extractingJsonPathNumberValue("@.amount")
            .isEqualTo(2.0);
    }

    @Test
    void inventoryItemDeserializationTest() throws IOException {
       String expected = """
                {
                    "name": "Box",
                    "description": "Cardboard box",
                    "quantity": 5,
                    "amount": 2.0
                }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new InventoryItem("Box", "Cardboard box", 5, BigDecimal.valueOf(2.0)));

       assertThat(json.parseObject(expected).getName()).isEqualTo("Box");
       assertThat(json.parseObject(expected).getDescription()).isEqualTo("Cardboard box");
       assertThat(json.parseObject(expected).getQuantity()).isEqualTo(5);
       assertThat(json.parseObject(expected).getAmount()).isEqualTo(2.0);
    }
}