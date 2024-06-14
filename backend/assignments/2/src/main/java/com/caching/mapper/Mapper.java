package com.caching.mapper;

import com.caching.dto.AddressDTO;
import com.caching.dto.CoordinateDTO;
import com.caching.models.Address;
import com.caching.models.Coordinate;

public class Mapper {
    /**
     * to map coordinates to coordinates data transfer object
     * @param coordinate: object of class coordinate
     * @return : dto of coordinate
     */
    public CoordinateDTO mapCoordinateDTO(Coordinate coordinate) {
        return new CoordinateDTO(coordinate.getLatitude(),coordinate.getLongitude());
    }

    /**
     * to map address to data transfer object
     * @param address : object of class address
     * @return : dto of address
     */
    public AddressDTO mapAddressDTO(Address address) {
        return new AddressDTO(address.getAddress());
    }
}
