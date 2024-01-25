package com.kdu.springbootvehicle.exception_handling;

public class CustomException extends IndexOutOfBoundsException {
    public CustomException(String message) {
        super(message);
    }
}
