package com.caching.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRepo {
    private ApiRepo(){
        /*private constructor to hide the implicit public one*/
    }

    /**
     * to process the backend api request
     * @param apiurl : url of api
     * @return : response of the http request
     */
    public static String sendHTTPRequest(String apiurl) throws Exception {
        URL url=new URL(apiurl);
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line=reader.readLine())!=null){
            response.append(line);
        }
        return response.toString();
    }
}
