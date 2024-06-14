package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    /**
     * to handle BadRequestExceptions occurring during programs execution
     * @param ex : exception object
     * @return : response entity containing error dto
     */
    @ExceptionHandler(value={BadRequestException.class})
    public ResponseEntity<ErrorDTO> badRequestException(BadRequestException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + "[Bad Request Exception]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errordto, HttpStatus.BAD_REQUEST);
    }
    /**
     * to handle generic tExceptions occurring during programs execution
     * @param ex : exception object
     * @return : response entity containing error dto
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> genericExceptions(Exception ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + " [Parent Exception]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errordto, HttpStatus.BAD_REQUEST);
    }
    /**
     * to handle CustomExceptions occurring during programs execution
     * @param ex : exception object
     * @return : response entity containing error dto
     */
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorDTO> customExceptions(CustomException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + " [Custom Exception]", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errordto, HttpStatus.UNAUTHORIZED);
    }
}
