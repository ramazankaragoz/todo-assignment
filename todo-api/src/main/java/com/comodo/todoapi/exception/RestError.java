package com.comodo.todoapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RestError {
    private Date timeStamp;
    private String message;
    private String details;
}
