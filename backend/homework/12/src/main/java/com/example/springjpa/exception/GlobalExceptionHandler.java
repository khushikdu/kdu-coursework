package com.example.springjpa.exception;

import com.example.springjpa.dto.ErrorDTO;
import com.example.springjpa.exception.custom.CustomException;
import com.example.springjpa.exception.custom.ShiftUserDeletionInvalid;
import com.example.springjpa.exception.custom.ShiftUserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for CustomException.
     * @param ex CustomException object
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(CustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [MyCustomException]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(ShiftUserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleShiftUserNotFoundException(ShiftUserNotFound ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ShiftUserDeletionInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleShiftUserInvalidDeletionException(ShiftUserDeletionInvalid ex) {
        return ex.getMessage();
    }
}
