package com.example.springjdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftType {
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
