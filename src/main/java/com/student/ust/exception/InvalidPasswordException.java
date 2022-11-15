package com.student.ust.exception;

/**
 * The type Invalid password exception.
 */
public class InvalidPasswordException extends BuisnessException{
    /**
     * Instantiates a new Invalid password exception.
     */
    public InvalidPasswordException(){
        super("Format is invalid");
    }
}
