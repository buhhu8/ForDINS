package org.dins.exception;

public class NumberNotFoundException extends RuntimeException{

    public NumberNotFoundException(Integer numberId){
        super("Number with such ID: " + numberId + " does not exist");
    }

}
