package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.AddUserRequest;
import com.kdu.smarthome.dto.requests.HouseRequest;
import com.kdu.smarthome.dto.response.AddUserResponse;
import com.kdu.smarthome.dto.response.GetHouseResponse;
import com.kdu.smarthome.dto.response.HouseDetailsResponse;
import com.kdu.smarthome.model.House;
import com.kdu.smarthome.services.HouseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;
    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * to add a new house to the list of houses
     * @param house: house object
     * @return : response entity
     */
    @PostMapping
    public ResponseEntity<House> addHouse(@RequestBody House house) {
        House addedHouse=houseService.addHouse(house);
        return ResponseEntity.ok(addedHouse);
    }

    /**
     * to check the validity of an existing house and add users to it
     * @param houseId : String houseId
     * @param addUserRequest : user request object
     * @param httpServletRequest: to check the authorisation
     * @return : add response
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<AddUserResponse> addUser(@PathVariable Long houseId,
                                                   @RequestBody AddUserRequest addUserRequest,
                                                   HttpServletRequest httpServletRequest){
        if(httpServletRequest.getHeader("Authorization")==null) {
            return new ResponseEntity<>(null,HttpStatus.valueOf(401));
        }
        String message= houseService.addUsers(houseId,addUserRequest.getUsername());
        AddUserResponse addUserResponse=new AddUserResponse();
        addUserResponse.setMessage(message);
        addUserResponse.setCustomMessage("User unauthorized");
        addUserResponse.setHttpStatusCode(HttpStatus.OK.toString());

        return new ResponseEntity<>(addUserResponse,HttpStatus.OK);
    }

    /**
     * to return the list of houses
     * @return : list to houses
     */
    @GetMapping
    public GetHouseResponse getHouseResponse() {
        List<House> houseList= houseService.getAllHouses();
        GetHouseResponse response=new GetHouseResponse();
        response.setMessage("List Retrieved successfully");
        response.setHouseList(houseList.toString());
        response.setHttpStatus(HttpStatus.OK);

        return response;
    }

    /**
     * to update a house address
     * @param houseId: id of the house
     * @param houseRequest : new address
     * @return : updated details of house
     */
    @PutMapping
    public GetHouseResponse updateHouseAddress(@RequestParam String houseId,
                                               @RequestBody HouseRequest houseRequest) {
        return houseService.updateHouse(houseId,houseRequest);
    }

    /**
     * to get deatils of the house
     * @param houseId : id of the house
     * @return : details of the house
     */
    @GetMapping("/{houseId}")
    public HouseDetailsResponse getDetails(@PathVariable String houseId){
        return houseService.getHouseDetails(houseId);
    }
}
