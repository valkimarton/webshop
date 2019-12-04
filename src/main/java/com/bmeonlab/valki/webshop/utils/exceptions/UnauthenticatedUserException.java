package com.bmeonlab.valki.webshop.utils.exceptions;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException(String s){
        super(s);
    }
}
