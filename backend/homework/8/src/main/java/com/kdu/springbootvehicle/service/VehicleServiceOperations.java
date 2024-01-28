package com.kdu.springbootvehicle.service;

import com.kdu.springbootvehicle.dto.VehicleDTO;
import com.kdu.springbootvehicle.entities.Vehicle;
import com.kdu.springbootvehicle.mapper.VehicleMapper;
import com.kdu.springbootvehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VehicleServiceOperations implements VehicleService{
    private final VehicleRepository vehicleRepository=new VehicleRepository();
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
    public void addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle= VehicleMapper.mapToVehicle(vehicleDTO);
        vehicleRepository.addVehicle(vehicle);
    }

    @Override
    public Vehicle getByID(int id){
        return vehicleRepository.findVehicle(id);
    }

    @Override
    public void updateVehicle(int id,VehicleDTO vehicleDTO) {
        Vehicle updateVehicle=VehicleMapper.mapToVehicle(vehicleDTO);
        vehicleRepository.updateVehicle(id,updateVehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        vehicleRepository.deleteVehicle(id);
    }

    public Vehicle mostExpensive(){
        return vehicleRepository.mostExpensive();
    }
    public Vehicle leastExpensive() {
        return vehicleRepository.leastExpensive();
    }
}
