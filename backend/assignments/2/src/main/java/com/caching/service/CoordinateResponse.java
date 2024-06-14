package com.caching.service;

import com.caching.models.Coordinate;
import com.caching.repository.ApiRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
public class CoordinateResponse {
    private static final Logger logger = LoggerFactory.getLogger(CoordinateResponse.class);
    private CoordinateResponse(){
        /*private constructor to hide the implicit one*/
    }

    @Value("${api-key}")
    private static final String KEY="d4627552d3a6a232880f9bd3b5f1f458";

    /**
     * to extract the coordinates in the required format from the json data
     * @param address: address of the place
     * @return : coordinates in the required format
     */
    public static Coordinate getCoordinates(String address) {
        String url=String.format("http://api.positionstack.com/v1/forward?access_key=%s&query=%s",KEY,address);
        Coordinate coordinate=null;
        try{
            String response=ApiRepo.sendHTTPRequest(url);
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            JSONObject result=jsonArray.getJSONObject(0);
            double latitude =result.getDouble("latitude");
            double longitude = result.getDouble("longitude");
            coordinate=new Coordinate(latitude,longitude);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return coordinate;
    }
}
