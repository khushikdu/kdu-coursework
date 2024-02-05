package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * data transfer object class for errors
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private int statusCode;
}
