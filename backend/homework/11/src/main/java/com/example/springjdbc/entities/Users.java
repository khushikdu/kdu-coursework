package com.example.springjdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
public class Users {
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
