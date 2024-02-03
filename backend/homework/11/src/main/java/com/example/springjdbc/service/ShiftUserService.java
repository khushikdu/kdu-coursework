package com.example.springjdbc.service;

import com.example.springjdbc.dto.ShiftUserDTO;
import com.example.springjdbc.respository.ShiftUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ShiftUserService {
    private ShiftUserDAO shiftUserDAO;
    @Autowired
    public ShiftUserService(ShiftUserDAO shiftUserDAO) {
        this.shiftUserDAO = shiftUserDAO;
    }
    @Transactional
    public void addService(ShiftUserDTO shiftUserDTO) {
        shiftUserDAO.add(shiftUserDTO);
    }
}
