package com.gallegos.vodafone.service;

public class MissingNumberException extends RuntimeException{

    public MissingNumberException(String message){
        super(message);
    }

}
