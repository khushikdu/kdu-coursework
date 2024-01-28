package com.kdu.springbootvehicle.mapper;

import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;

public class VehicleMapper {
    private VehicleMapper(){
    }
    public static VehicleDTO mapToVehicleDto(Vehicle vehicle){
        return new VehicleDTO(vehicle.getName(), vehicle.getPrice());
    }
    public static Vehicle mapToVehicle(VehicleDTO vehicleDTO) {
        return new Vehicle(vehicleDTO.getName(),vehicleDTO.getPrice());
    }
}
