package com.example.springbootapidemo.exception;

import com.example.springbootapidemo.dto.ErrorDTO;
import com.example.springbootapidemo.exception.custom.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * custom exception handles
     * @param ex: exception
     * @return : response entity
     */
    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [MyCustomException]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * custom exception handles
     * @param ex: exception
     * @return : response entity
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> allKindOfExceptions(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [Parent Exception]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}