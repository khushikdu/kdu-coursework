package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.requests.AddDeviceRequest;
import com.kdu.smarthome.dto.requests.RegisterDeviceRequest;
import com.kdu.smarthome.dto.response.DefaultAddResponse;
import com.kdu.smarthome.model.Device;
import com.kdu.smarthome.model.House;
import com.kdu.smarthome.model.Room;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;
    @Autowired
    public DeviceService(DeviceRepository deviceRepository, HouseRepository houseRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * to save a device in device repository
     * @param request: new device object
     * @return : default response entity
     */
    public DefaultAddResponse registerDevice(RegisterDeviceRequest request){
        Device device=new Device();
        device.setKickstonId(request.getKickstonId());
        device.setDeviceUsername(request.getDeviceUsername());
        device.setDevicePassword(request.getDevicePassword());
        device.setAvailable(true);
        deviceRepository.save(device);
        return new DefaultAddResponse("Device Register Successfully\n",request.toString(), HttpStatus.OK);
    }
    /**
     * to save a device in device repository
     * @param addDeviceRequest: new device object
     * @return : default response entity
     */
    public DefaultAddResponse addDevice(AddDeviceRequest addDeviceRequest) {
        Long houseId=Long.parseLong(addDeviceRequest.getHouseId());
        Long roomId=Long.parseLong(addDeviceRequest.getRoomId());

        House house=houseRepository.findById(houseId)
                .orElseThrow(()-> new RuntimeException("House not found"));

        Optional<Object> optionalRoom=roomRepository.findByIdAndHouse(roomId,house);
        Room room=(Room) optionalRoom.orElseThrow(()->
                new RuntimeException("Rooms not found"));

        Device device=new Device();
        device.setAvailable(true);
        device.setKickstonId(addDeviceRequest.getKickstonId());
        device.setRoom(room);
        deviceRepository.save(device);

        return new DefaultAddResponse("Added Successfully",addDeviceRequest.toString(),HttpStatus.OK);
    }
}
