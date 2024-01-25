package com.kdu.springbootvehicle.service;

import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VehicleService {
    public List<VehicleDTO> getVehicleList();
    public void addVehicle(VehicleDTO vehicleDTO);
    public Vehicle getByID(int id);

    public void updateVehicle(int id,VehicleDTO vehicleDTO);
    public void deleteVehicle(int id);
    public Vehicle mostExpensive();
    public Vehicle leastExpensive();
}
