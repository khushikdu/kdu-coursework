package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftDTO;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiftController {
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    private final ShiftService shiftService;
    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /**
     * to call post mapping api for adding the shift
     * @param shiftDTO: shiftdto object
     * @return : response String
     */
    @PostMapping("/shift")
    public ResponseEntity<String> addShift(@RequestBody ShiftDTO shiftDTO) {
        Logging.printLogger("Adding Shift "+shiftDTO,loggerTypeInfo);
        shiftService.addService(shiftDTO);
        return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
    }
}
