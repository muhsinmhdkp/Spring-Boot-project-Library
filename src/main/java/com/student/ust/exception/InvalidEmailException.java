package com.student.ust.exception;

/**
 * The type Invalid email exception.
 */
public class InvalidEmailException  extends BuisnessException{
    /**
     * Instantiates a new Invalid email exception.
     */
    public InvalidEmailException(){
        super("format is invalid");
    }
}
