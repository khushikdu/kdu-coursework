package com.kdu.smarthome.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDeviceRequest {
    private String houseId;
    private String roomId;
    private String kickstonId;
}
