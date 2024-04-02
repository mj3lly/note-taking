package com.ota.notetaking.exception;

public class ExistingResourceException extends RuntimeException {
    public ExistingResourceException(String errorMessage) {
        super(errorMessage);
    }

}

