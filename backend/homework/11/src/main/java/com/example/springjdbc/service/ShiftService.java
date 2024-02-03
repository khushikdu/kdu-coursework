package com.example.springjdbc.service;

import com.example.springjdbc.respository.ShiftDAO;
import com.example.springjdbc.dto.ShiftDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ShiftService {
    private final ShiftDAO shiftDAO;

    @Autowired
    public ShiftService(ShiftDAO shiftDAO) {
        this.shiftDAO = shiftDAO;
    }
    @Transactional
    public void addService(ShiftDTO shiftDTO){
        shiftDAO.add(shiftDTO);
    }
}
