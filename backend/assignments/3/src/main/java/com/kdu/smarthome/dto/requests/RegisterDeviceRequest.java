package com.kdu.smarthome.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDeviceRequest {
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
}
