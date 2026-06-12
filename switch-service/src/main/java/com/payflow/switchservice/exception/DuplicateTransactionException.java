package com.payflow.switchservice.exception;

public class DuplicateTransactionException
        extends RuntimeException {

    public DuplicateTransactionException(
            String message
    ) {
        super(message);
    }
}