package com.kdu.springbootvehicle.repository;

import com.kdu.springbootvehicle.entities.Vehicle;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class VehicleRepository {
    private List<Vehicle> vehicleList=new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }
    public Vehicle findVehicle(int id) {
        return vehicleList.get(id);
    }
    public void updateVehicle(int id, Vehicle newVehicle){
        vehicleList.get(id).setName(newVehicle.getName());
        vehicleList.get(id).setPrice(newVehicle.getPrice());

    }
    public void deleteVehicle(int id) {
        vehicleList.remove(id);
    }

    public Vehicle leastExpensive() {
        return vehicleList.stream()
                .min((v1,v2)->Double.compare(v1.getPrice(),v2.getPrice()))
                .orElse(null);
    }

    public Vehicle mostExpensive() {
        return vehicleList.stream()
                .max((v1,v2)->Double.compare(v1.getPrice(),v2.getPrice()))
                .orElse(null);
    }
}
