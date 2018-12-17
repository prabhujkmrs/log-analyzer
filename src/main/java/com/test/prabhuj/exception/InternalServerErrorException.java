package com.test.prabhuj.exception;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException() {
        super("HTTP 500 Internal Server Error");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

}
