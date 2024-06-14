package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.AddRoomToHouseRequest;
import com.kdu.smarthome.dto.response.AddRoomToHouseResponse;
import com.kdu.smarthome.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final HouseService houseService;

    @Autowired
    public RoomController(HouseService houseService) {
        this.houseService=houseService;
    }

    /**
     * to add room to a house
     * @param houseId: house id
     * @param request: room details
     * @return :response entity
     */
    @PostMapping
    public AddRoomToHouseResponse addRoomToHouse(@RequestParam String houseId,
                                                 @RequestBody AddRoomToHouseRequest request) {
        return houseService.addRoomToHouse(houseId,request);
    }
}
