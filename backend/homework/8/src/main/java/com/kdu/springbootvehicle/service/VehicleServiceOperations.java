package com.kdu.springbootvehicle.service;

import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;
import com.kdu.springbootvehicle.mapper.VehicleMapper;
import com.kdu.springbootvehicle.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class VehicleServiceOperations implements VehicleService{
    private VehicleRepository vehicleRepository;
    public List<VehicleDTO> getVehicleList(){
        List<Vehicle> vehicleList=vehicleRepository.getVehicleList();
        List<VehicleDTO> vehicleDTOList=new ArrayList<>();
        VehicleDTO vehicleDTO;
        for (Vehicle vehicle: vehicleList){
            vehicleDTO=VehicleMapper.mapToVehicleDto(vehicle);
            vehicleDTOList.add(vehicleDTO);
        }
        return vehicleDTOList;
    }
    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle= VehicleMapper.mapToVehicle(vehicleDTO);
        vehicleRepository.addVehicle(vehicle);
        return vehicleDTO;
    }

    @Override
    public VehicleDTO getByName(String name) {
        Vehicle vehicle=vehicleRepository.findVehicle(name);
        return VehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle updateVehicle=VehicleMapper.mapToVehicle(vehicleDTO);
        vehicleRepository.updateVehicle(vehicleDTO.getName(),updateVehicle);
        return vehicleDTO;
    }

    @Override
    public void deleteVehicle(String name) {
        vehicleRepository.deleteVehicle(name);
    }
}
