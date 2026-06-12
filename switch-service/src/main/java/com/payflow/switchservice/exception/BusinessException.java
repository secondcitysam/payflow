package com.payflow.switchservice.exception;

public class BusinessException
        extends RuntimeException {

    public BusinessException(
            String message
    ) {
        super(message);
    }
}