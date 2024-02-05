package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
/**
 * data transfer object class for users
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private int login;
    private String timezone;
    private UUID tenantID;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
