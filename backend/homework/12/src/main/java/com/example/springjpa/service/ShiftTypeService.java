package com.example.springjpa.service;

import com.example.springjpa.entity.ShiftType;
import com.example.springjpa.exception.custom.CustomException;
import com.example.springjpa.interfaces.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftTypeService {

    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    /**
     * to save a shift type
     * @param shiftType The shift type to be saved.
     * @throws CustomException If an error occurs during the shift type saving process.
     */
    @Transactional
    public void saveShiftType(ShiftType shiftType) {
        try {
            shiftTypeRepository.save(shiftType);
        } catch (Exception e) {
            throw new CustomException("Failed to save shift type.");
        }
    }

    /**
     * to retrieves all shift types belonging to a specific tenant
     * @param tenantId The ID of the tenant.
     * @return The list of shift types belonging to the specified tenant.
     */
    public List<ShiftType> getShiftTypes(UUID tenantId) {
        return shiftTypeRepository.findByTenantId(tenantId);
    }
}
