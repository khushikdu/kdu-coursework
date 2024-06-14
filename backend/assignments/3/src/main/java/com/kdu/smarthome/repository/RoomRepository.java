package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.House;
import com.kdu.smarthome.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Optional<Object> findByIdAndHouse(Long roomId, House house);
}
