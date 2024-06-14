package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInventoryResponse {
    private String responseMessage;
    private HttpStatus httpStatus;
}
