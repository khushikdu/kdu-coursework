package com.example.springjdbc.exceptions;

import com.example.springjdbc.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorDTO> customExceptionHandler(CustomException ex) {
        ErrorDTO errorDTO=new ErrorDTO(ex.getMessage()+"(Custom Exception)", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> globalExceptionHandler(Exception ex) {
        ErrorDTO errorDTO=new ErrorDTO(ex.getMessage()+"(Global Exception)", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
