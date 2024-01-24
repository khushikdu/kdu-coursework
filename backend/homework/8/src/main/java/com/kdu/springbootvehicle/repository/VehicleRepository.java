package com.kdu.springbootvehicle.repository;

import com.kdu.springbootvehicle.entities.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class VehicleRepository {
    List<Vehicle> vehicleList=new ArrayList<>();

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }
    public Vehicle findVehicle(String name) {
        for(Vehicle vehicle: vehicleList){
            if(vehicle.getName().equalsIgnoreCase(name)){
                return vehicle;
            }
        }
        return null;
    }
    public void updateVehicle(String name, Vehicle newVehicle){
        for(Vehicle vehicle: vehicleList){
            if(vehicle.getName().equalsIgnoreCase(name)){
                vehicle.setName(newVehicle.getName());
                vehicle.setPrice(newVehicle.getPrice());
            }
        }
    }
    public void deleteVehicle(String name) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getName().equalsIgnoreCase(name)) {
                vehicleList.remove(vehicle);
                break;
            }
        }
    }
}
