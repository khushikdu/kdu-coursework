package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftTypeDTO;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiftTypeController {
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    private final ShiftTypeService shiftTypeService;
    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     * to call post mapping api for adding the shift type
     * @param shiftTypeDTO: dto object
     * @return : response entity string
     */
    @PostMapping("/shiftType")
    public ResponseEntity<String> addShift(@RequestBody ShiftTypeDTO shiftTypeDTO) {
        Logging.printLogger("Adding Shift "+shiftTypeDTO,loggerTypeInfo);
        shiftTypeService.addService(shiftTypeDTO);
        return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
    }
}
