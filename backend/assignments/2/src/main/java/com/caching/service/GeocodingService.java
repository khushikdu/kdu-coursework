package com.caching.service;

import com.caching.models.Coordinate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class GeocodingService {
    @CacheEvict(value = "geocoding",key = "#address")
    public void evictCache(String address){
        /*the cacheable annotation was showing an error without the cacheEvict */
    }

    /**
     * function to get the coordinate response
     * @param address: parameter containing the address
     * @return : response in the form of coordinates
     */
    @Cacheable(value = "geocoding", key = "#address",condition = "#address!='goa'")
    public Coordinate getCoordinate(String address){
        return CoordinateResponse.getCoordinates(address);
    }
}
