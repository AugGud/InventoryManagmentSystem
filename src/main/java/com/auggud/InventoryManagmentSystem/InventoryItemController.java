package com.auggud.InventoryManagmentSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class IventoryItemController {

    @Autowired
    InventoryItemService invItemService;

    @RequestMapping(value="/inventoryitems", method=RequestMethod.POST)
    public InventoryItem createInventoryItem(@RequestBody InventoryItem invItem) {
    return invItemService.createInventoryItem(invItem);
    }

    @RequestMapping(value="/inventoryitems", method=RequestMethod.GET)
    public List<InventoryItem> readInventoryItems() {
        return invItemService.getInventoryItems();
    }

    @RequestMapping(value="/inventoryitems/{invItemId}", method=RequestMethod.PUT)
    public InventoryItem readInventoryItem(@PathVariable(value = "invItemId") Long id, @RequestBody InventoryItem invItemDetails) {
        return invItemService.updateInventoryItem(id, invItemDetails);
    }

    @RequestMapping(value="/inventoryitems/{invItemId}", method=RequestMethod.DELETE)
    public void deleteInventoryItem(@PathVariable(value = "invItemId") Long id) {
        invItemService.deleteInventoryItem(id);
    }
}