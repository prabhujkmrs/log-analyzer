package com.test.prabhuj.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("HTTP 404 Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
