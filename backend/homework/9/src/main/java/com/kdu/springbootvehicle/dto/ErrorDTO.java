package com.kdu.springbootvehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {
    String message;
    HttpStatus statusCode;

    @Override
    public String toString() {
        return "ErrorDTO{ " +
                "\n\tmessage :'" + message + '\'' +
                ", \n\tstatusCode : " + statusCode +
                "\n}";
    }
}
