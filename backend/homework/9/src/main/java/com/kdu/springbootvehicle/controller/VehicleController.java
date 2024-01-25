package com.kdu.springbootvehicle.controller;

import com.kdu.springbootvehicle.dto.ErrorDTO;
import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;
import com.kdu.springbootvehicle.exception_handling.CustomException;
import com.kdu.springbootvehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class VehicleController {
    private static final Logger logger= LoggerFactory.getLogger(VehicleController.class);
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDTO vehicle) {
        vehicleService.addVehicle(vehicle);
        logger.info("\nNew Vehicle added successfully.");
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public Vehicle getVehicle(@PathVariable("id") int id) throws CustomException{
        Vehicle vehicle=null;
        try {
            vehicle=vehicleService.getByID(id);
            return vehicle;
        } catch(IndexOutOfBoundsException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            logger.warn("\nException handled by Custom Exception\n{}",errorDTO);
            throw new CustomException("Vehicle not found");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id,@RequestBody VehicleDTO updatedVehicle)
                                                                                        throws CustomException  {
        try {
            vehicleService.updateVehicle(id, updatedVehicle);
            return new ResponseEntity<>("Vehicle updated successfully", HttpStatus.CREATED);
        }catch (IndexOutOfBoundsException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            logger.warn("\nException handled by Custom Exception\n{}", errorDTO);
            throw new CustomException("Vehicle not found");
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>("The vehicle is deleted", HttpStatus.OK);
        } catch (Exception e){
            logger.warn("\nException NOT handled by Custom Exception");
            logger.error(e.getMessage());
        }
        return null;
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
