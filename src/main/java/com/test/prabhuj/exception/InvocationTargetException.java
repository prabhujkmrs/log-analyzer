package com.test.prabhuj.exception;

public class InvocationTargetException extends RuntimeException {

    public InvocationTargetException()
    {
        super("HTTP Invocation Target Exception");
    }
    public InvocationTargetException(String message) {
            super(message);
        }
}
