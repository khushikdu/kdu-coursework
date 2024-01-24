package com.kdu.springbootvehicle.service;

import com.kdu.springbootvehicle.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    public List<VehicleDTO> getVehicleList();
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO);
    public VehicleDTO getByName(String name);
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO);
    public void deleteVehicle(String name);
}
