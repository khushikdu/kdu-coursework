package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.requests.AddRoomToHouseRequest;
import com.kdu.smarthome.dto.requests.HouseRequest;
import com.kdu.smarthome.dto.response.AddRoomToHouseResponse;
import com.kdu.smarthome.dto.response.GetHouseResponse;
import com.kdu.smarthome.dto.response.HouseDetailsResponse;
import com.kdu.smarthome.model.House;
import com.kdu.smarthome.model.Room;
import com.kdu.smarthome.model.Users;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;
    @Autowired
    public HouseService(HouseRepository houseRepository,RoomRepository roomRepository) {
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * to add a new house to the house repository
     * @param house: new house to be added
     * @return : details of house added
     */
    public House addHouse(House house) {
        houseRepository.save(house);
        return house;
    }

    /**
     * to add users to the house
     * @param houseId : id of the house
     * @param username : username of the user
     * @return : status of added or not
     */
    public String addUsers(Long houseId,String username) {
        Optional<House> house=houseRepository.findById(houseId);
        if(house.isPresent()) {
            House houseObj=house.get();
            Users users = new Users();
            users.setUsername(username);

            houseObj.getUsersList().add(users);
            return "User added successfully";
        } else {
            return "House not found";
        }
    }

    /**
     * to get the list of all the houses
     * @return : list of all the houses in the repository
     */
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    /**
     * to update an existing house
     * @param houseId: id of the house
     * @param houseRequest: new data to be updated
     * @return : details of updated house
     */
    public GetHouseResponse updateHouse(String houseId, HouseRequest houseRequest) {
        Optional<House> house=houseRepository.findById(Long.parseLong(houseId));
        if(house.isPresent()) {
            House house1=house.get();
            house1.setAddress(houseRequest.getAddress());
            houseRepository.save(house1);
            return new GetHouseResponse("Success","Updated Successfully!", HttpStatus.OK);
        } else {
            return new GetHouseResponse("Failed","Not Updated!", HttpStatus.valueOf(404));
        }
    }

    /**
     * to add room to the house
     * @param houseId: id of the house
     * @param newRoom: details of new room that has to be added
     * @return : default response object
     */
    public AddRoomToHouseResponse addRoomToHouse(String houseId, AddRoomToHouseRequest newRoom) {
        Optional<House> optionalHouse=houseRepository.findById(Long.parseLong(houseId));

        if (optionalHouse.isPresent()) {
            House house=optionalHouse.get();

            Room room=new Room();
            room.setName(newRoom.getRoomName());

            roomRepository.save(room);
            List<Room> rooms=house.getRooms();
            rooms.add(room);
            house.setRooms(rooms);
            houseRepository.save(house);

            return new AddRoomToHouseResponse("Room Added successfully", room, HttpStatus.OK);
        } else {
            return new AddRoomToHouseResponse("Room not added",null,HttpStatus.valueOf(404));
        }
    }

    /**
     * to get the details of the house whose id is given
     * @param houseId: id of the house
     * @return : details to the house or alternative message
     */
    public HouseDetailsResponse getHouseDetails(String houseId) {
        Optional<House> optionalHouse=houseRepository.findById(Long.parseLong(houseId));
        if(optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            String object= houseId.concat(
                    house.getHouseName());
            return new HouseDetailsResponse("Details of the house fetched successfully",object,HttpStatus.OK);
        }
        else {
            return new HouseDetailsResponse("Details of the house could not be fetched",
                    "Error finding details",HttpStatus.valueOf(400));
        }
    }
}
