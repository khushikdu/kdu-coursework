package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * data transfer object class for all tenants
 */
@Data
@AllArgsConstructor
public class AllTenantsDTO {
    private UserDTO userDTO;
    private ShiftDTO shiftDTO;
    private ShiftUserDTO shiftUserDTO;
    private ShiftTypeDTO shiftTypeDTO;
}
