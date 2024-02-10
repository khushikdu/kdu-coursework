package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.AddDeviceRequest;
import com.kdu.smarthome.dto.requests.RegisterDeviceRequest;
import com.kdu.smarthome.dto.response.DefaultAddResponse;
import com.kdu.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {
    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * to register a valid device
     * @param request: device to be registered
     * @return : required response
     */
    @PostMapping("/register")
    public DefaultAddResponse registerDevices(@RequestBody RegisterDeviceRequest request){
        return deviceService.registerDevice(request);
    }

    /**
     * to add a valid device
     * @param request: device to be added
     * @return :required response
     */
    @PostMapping("/add")
    public DefaultAddResponse addDevice(@RequestBody AddDeviceRequest request) {
        return  deviceService.addDevice(request);
    }
}
