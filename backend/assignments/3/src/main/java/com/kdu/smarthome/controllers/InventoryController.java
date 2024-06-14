package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.AddInventoryRequest;
import com.kdu.smarthome.dto.response.DefaultAddResponse;
import com.kdu.smarthome.dto.response.GetInventoryResponse;
import com.kdu.smarthome.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * to retrieve all inventory items
     * @return : list of items
     */
    @GetMapping
    public GetInventoryResponse getAllInventoryItems() {
        String response=inventoryService.findAll();
        if(!response.isEmpty()){
            return new GetInventoryResponse(response, HttpStatus.OK);
        } else {
            return new GetInventoryResponse("Inventory List is empty",HttpStatus.OK);
        }
    }

    /**
     * to add inventory items
     * @param request: object to be added
     * @return : success/failure response
     */
    @PostMapping
    public DefaultAddResponse addInventory(@RequestBody AddInventoryRequest request) {
        return inventoryService.addInventory(request);
    }
}
