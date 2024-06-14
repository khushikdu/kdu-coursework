package com.kdu.smarthome.services;

import com.kdu.smarthome.model.Room;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * to create a new room
     * @param room: new room entity
     * @return : details of added room
     */
    public String createRoom(Room room) {
        roomRepository.save(room);
        return room.toString();
    }
}
