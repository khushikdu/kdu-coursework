package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantInfoDTO {
    private UserDTO userDTO;
    private ShiftDTO shiftDTO;
    private ShiftTypeDTO shiftTypeDTO;
    private ShiftUserDTO shiftUserDTO;
}
