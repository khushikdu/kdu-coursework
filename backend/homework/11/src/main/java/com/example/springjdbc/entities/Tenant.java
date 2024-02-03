package com.example.springjdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
public class Tenant {
    private UUID id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
