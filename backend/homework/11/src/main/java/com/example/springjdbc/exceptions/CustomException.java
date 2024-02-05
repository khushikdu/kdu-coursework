package com.example.springjdbc.exceptions;

public class CustomException extends IndexOutOfBoundsException{
    /**
     * parameterised constructor for customhandler
     * @param msg: error message
     */
    public CustomException(String msg){
        super(msg);
    }
}
