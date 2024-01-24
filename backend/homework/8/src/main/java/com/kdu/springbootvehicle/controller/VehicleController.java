package com.kdu.springbootvehicle.controller;

import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;
import com.kdu.springbootvehicle.mapper.VehicleMapper;
import com.kdu.springbootvehicle.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {
    private List<VehicleDTO> vehicleList=new ArrayList<>();
    VehicleRepository vehicleRepository;
    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) {
        VehicleDTO vehicleDTO= VehicleMapper.mapToVehicleDto(vehicle);
        vehicleList.add(vehicleDTO);
        return ResponseEntity.ok("Vehicle added successfully");
    }
    @GetMapping("/vehicle/name")
    public Vehicle getUser(@RequestParam String name) {
        for (VehicleDTO vehicleDTO: vehicleList) {
            if(vehicleDTO.getName().equalsIgnoreCase(name)) {
                return VehicleMapper.mapToVehicle(vehicleDTO);
            }
        }
        return null;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id,@RequestBody Vehicle updatedVehicle) {
        if (id >= 0 && id < vehicleList.size()) {
            VehicleDTO existingVehicle = vehicleList.get(id);
            VehicleDTO newVehicle = VehicleMapper.mapToVehicleDto(updatedVehicle);
            existingVehicle.setName(newVehicle.getName());
            existingVehicle.setPrice(newVehicle.getPrice());
            return ResponseEntity.ok("Vehicle updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if(id>=0&&id<vehicleList.size()){
            vehicleList.remove(id);
            return ResponseEntity.ok().body("Vehicle deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
    }
    @GetMapping("/vehicle/max")
    public Vehicle getMax() {
        VehicleDTO vehicleDTO =vehicleList.stream()
                .max((v1,v2)->Double.compare(v1.getPrice(),v2.getPrice()))
                .orElse(null);
        return VehicleMapper.mapToVehicle(vehicleDTO);
    }
    @GetMapping("/vehicle/min")
    public Vehicle getMin() {
        VehicleDTO vehicleDTO =vehicleList.stream()
                .min((v1,v2)->Double.compare(v1.getPrice(),v2.getPrice()))
                .orElse(null);
        return VehicleMapper.mapToVehicle(vehicleDTO);
    }
}
