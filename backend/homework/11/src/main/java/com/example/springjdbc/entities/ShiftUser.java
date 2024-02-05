package com.example.springjdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
public class ShiftUser {
    private UUID id;
    private UUID shiftID;
    private UUID userID;
    private UUID tenantID;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
