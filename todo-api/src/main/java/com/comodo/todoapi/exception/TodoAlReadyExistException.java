package com.comodo.todoapi.exception;

public class TodoAlReadyExistException extends RuntimeException{
    public TodoAlReadyExistException(String message) {
        super(message);
    }
}
