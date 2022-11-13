package com.student.ust.exception;

public class InvalidEmailException  extends BuisnessException{
    public InvalidEmailException(){
        super("format is invalid");
    }
}
