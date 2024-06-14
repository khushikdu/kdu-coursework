package com.caching.service;

import com.caching.models.Address;
import com.caching.repository.ApiRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class AddressResponse {
    private AddressResponse(){
            /*private constructor to hide the implicit public one*/
    }
    @Value("${api-key}")
    private static final Logger logger = LoggerFactory.getLogger(AddressResponse.class);

    private static final String KEY= "d4627552d3a6a232880f9bd3b5f1f458";

    /**
     * to get the address in the expected format
     * @param latitude: to store the latitude
     * @param longitude: to store the longitude
     * @return : address in the expected format
     */
    public static Address getAddress(double latitude, double longitude) {
        String url=String.format("http://api.positionstack.com/v1/reverse?access_key=%s&query=%f,%f",
                KEY,latitude,longitude);
        Address address=null;
        try{
            String response = ApiRepo.sendHTTPRequest(url);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject result = jsonArray.getJSONObject(0);
            address=new Address(result.getString("label"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return address;
    }
}
