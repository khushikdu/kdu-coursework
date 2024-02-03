package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
/**
 * data transfer object class for tenant
 */
@Data
@AllArgsConstructor
public class TenantDTO {
    private UUID id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}

