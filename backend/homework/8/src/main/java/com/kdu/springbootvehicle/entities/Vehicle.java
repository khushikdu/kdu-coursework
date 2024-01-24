package com.kdu.springbootvehicle.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String name;
    private double price;
}
