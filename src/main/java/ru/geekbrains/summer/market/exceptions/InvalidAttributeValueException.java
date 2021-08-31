package ru.geekbrains.summer.market.exceptions;

public class InvalidAttributeValueException extends RuntimeException{
    public InvalidAttributeValueException(String message){
        super(message);
    }
}
