package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllTenantsDTO {
    private UserDTO userDTO;
    private ShiftDTO shiftDTO;
    private ShiftUserDTO shiftUserDTO;
    private ShiftTypeDTO shiftTypeDTO;
}
