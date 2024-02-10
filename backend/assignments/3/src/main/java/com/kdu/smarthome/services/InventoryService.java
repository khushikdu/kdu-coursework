package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.requests.AddInventoryRequest;
import com.kdu.smarthome.dto.response.DefaultAddResponse;
import com.kdu.smarthome.model.InventoryItem;
import com.kdu.smarthome.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    /**
     * to get all the inventory items
     * @return : String of all the items present
     */
    public String findAll() {
        List<InventoryItem> inventoryItems=inventoryItemRepository.findAll();
        String response="";
        for(InventoryItem inventoryItem: inventoryItems){
            response=response.concat(inventoryItem.toString());
        }
        return response;
    }

    /**
     * to add a new item to the inventory
     * @param request: new inventory item to be added
     * @return : default response
     */
    public DefaultAddResponse addInventory(AddInventoryRequest request) {

        InventoryItem newItem = new InventoryItem(
                request.getKickstonId(),
                request.getDeviceUsername(),
                request.getDevicePassword(),
                LocalDateTime.now(),
                request.getManufactureFactoryPlace());
        inventoryItemRepository.save(newItem);
        return new DefaultAddResponse("Added successfully",newItem.toString(), HttpStatus.OK);
    }
}
