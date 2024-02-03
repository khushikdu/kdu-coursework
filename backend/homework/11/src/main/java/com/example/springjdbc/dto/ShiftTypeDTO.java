package com.example.springjdbc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
/**
 * data transfer object class for shift type
 */
@Data
@AllArgsConstructor
public class ShiftTypeDTO {
    private UUID id;
    private String uqName;
    private String description;
    private Boolean active;
    private String timeZone;
    private UUID tenantID;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}