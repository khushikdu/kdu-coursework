package com.caching.service;

import com.caching.models.Address;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class ReverseGeocodingService {
    @CacheEvict(value = "reverse-geocoding",key = "{#latitude,#longitude}")
    public void evictCache(String latitude, String longitude){
        /*the cacheable annotation was showing an error without the cacheEvict*/
    }

    /**
     * function to get the address response
     * @param latitude: to store the latitude
     * @param longitude: to store the longitude
     * @return : the response for the address request
     */
    @Cacheable(value = "reverse-geocoding", key = "{#latitude,#longitude}")
    public Address getAddress(double latitude, double longitude) {
        return AddressResponse.getAddress(latitude,longitude);
    }
}
