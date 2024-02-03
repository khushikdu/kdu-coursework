package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftUserDTO;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiftUserController {
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    private final ShiftUserService shiftUserService;
    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     * to call post mapping api for adding the shift user
     * @param shiftUserDTO: dto object
     * @return : response string
     */
    @PostMapping("/shiftUser")
    public ResponseEntity<String> addShift(@RequestBody ShiftUserDTO shiftUserDTO) {
        Logging.printLogger("Adding Shift "+shiftUserDTO,loggerTypeInfo);
        shiftUserService.addService(shiftUserDTO);
        return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
    }
}
