package com.example.springjdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Shift {
    private UUID id;
    private UUID shiftTypeID;
    private String name;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
