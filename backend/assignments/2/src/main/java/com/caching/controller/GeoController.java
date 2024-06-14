package com.caching.controller;

import com.caching.dto.AddressDTO;
import com.caching.dto.CoordinateDTO;
import com.caching.mapper.Mapper;
import com.caching.service.GeocodingService;
import com.caching.service.ReverseGeocodingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {
    /**
     * function to get the response for the requested address
     * @param address : String that stores the address or location
     * @return : ResponseEntity containing the coordinates dto
     */
    @GetMapping("/geocoding")
    public ResponseEntity<CoordinateDTO> getCoordinate(@RequestParam("address") String address) {
        return new ResponseEntity<>(new Mapper().mapCoordinateDTO(new GeocodingService().getCoordinate(address)),
                HttpStatus.OK);
    }

    /**
     * function to get the response for the requested coordinates
     * @param latitude: to store the latitude
     * @param longitude: to store the longitude
     * @return : ResponseEntity containing the address dto
     */
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<AddressDTO> getAddress(@RequestParam("latitude") double latitude,
                                                 @RequestParam("longitude") double longitude) {
        return new ResponseEntity<>(new Mapper().mapAddressDTO(new ReverseGeocodingService().getAddress(latitude, longitude)),
                HttpStatus.OK);
    }
}
