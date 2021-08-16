package ru.geekbrains.summer.market.exceptions;

public class EmptyValueException extends RuntimeException{
    public EmptyValueException(String message){
        super(message);
    }
}
