package com.example.springjdbc.service;

import com.example.springjdbc.dto.ShiftTypeDTO;
import com.example.springjdbc.respository.ShiftTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShiftTypeService {
    private final ShiftTypeDAO shiftTypeDAO;
    @Autowired
    public ShiftTypeService(ShiftTypeDAO shiftTypeDAO) {
        this.shiftTypeDAO = shiftTypeDAO;
    }
    /**
     * to add the dto object in the repository class
     * @param shiftTypeDTO : data transfer object
     */
    @Transactional
    public void addService(ShiftTypeDTO shiftTypeDTO) {
        shiftTypeDAO.add(shiftTypeDTO);
    }
}
