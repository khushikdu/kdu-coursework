package com.kdu.springbootvehicle.controller;

import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;
import com.kdu.springbootvehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class VehicleController {

    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDTO vehicle) {
        vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public Vehicle getVehicle(@PathVariable("id") int id) {
           return vehicleService.getByID(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id,@RequestBody VehicleDTO updatedVehicle) {
        vehicleService.updateVehicle(id,updatedVehicle);
            return new ResponseEntity<>("Vehicle updated successfully",HttpStatus.CREATED);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("The vehicle is deleted", HttpStatus.OK);
    }
    @GetMapping("/max")
    public Vehicle getExpensiveVehicle() {
        return vehicleService.mostExpensive();
    }
    @GetMapping("/min")
    public Vehicle getCheapVehicle() {
        return vehicleService.leastExpensive();
    }
}
