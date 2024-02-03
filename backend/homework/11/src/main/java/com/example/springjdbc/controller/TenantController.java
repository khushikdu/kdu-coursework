package com.example.springjdbc.controller;

import com.example.springjdbc.dto.AllTenantsDTO;
import com.example.springjdbc.dto.TenantDTO;
import com.example.springjdbc.entities.Tenant;
import com.example.springjdbc.exceptions.CustomException;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TenantController {
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;
    private final TenantService tenantService;
    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }
    @PostMapping("/addTenant")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO){
        try {
            int index =-1;
            index=tenantService.addService(tenantDTO);
            Logging.printLogger("Adding Tennat at index " + index, loggerTypeInfo);
            if(index==-1){
                throw new CustomException("Error adding tenant");
            }
            return new ResponseEntity<>("Added Sucessfully", HttpStatus.OK);
        } catch (CustomException e){
            Logging.printLogger("Exception handled by custom handler :"+e.getMessage(), Logging.LoggerType.ERROR);
        }
        return null;
    }
    @PostMapping("/addAllTenant")
    public ResponseEntity<String> addAllTenant(@RequestBody AllTenantsDTO allTenantsDTO){
        tenantService.addAllServiceAtOnce(allTenantsDTO);
        return new ResponseEntity<>("Added successfully", HttpStatus.OK);
    }
    @GetMapping("/allTenants")
    public ResponseEntity<List<Tenant>> getAllTenant() throws CustomException{
        try{
            List<Tenant> tenantList=tenantService.getAllTenant();
            if (tenantList==null){
                throw new CustomException("Error getting the list");
            }
            return new ResponseEntity<>(tenantList,HttpStatus.OK);
        }catch (CustomException e){
            Logging.printLogger("Exception handled by custom handler :"+e.getMessage(), Logging.LoggerType.ERROR);
        }
        return null;
    }
}
