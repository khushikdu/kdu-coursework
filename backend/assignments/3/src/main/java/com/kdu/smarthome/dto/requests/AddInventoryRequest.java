package com.kdu.smarthome.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInventoryRequest {
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private String manufactureFactoryPlace;
}
