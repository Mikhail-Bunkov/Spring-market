package ru.bunkov.market.exceptions;

public class InvalidAttributeValueException extends RuntimeException{
    public InvalidAttributeValueException(String message){
        super(message);
    }
}
