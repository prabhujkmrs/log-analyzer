package com.test.prabhuj.exception.handler;

import java.util.List;
import java.util.Objects;

/**
 * This is used to map a Java exception to an HTTP status with a relevant message.
 */
public class HttpExceptionResponse {

    private int status;
    private String message;
    private List<String> errors;

    public HttpExceptionResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpExceptionResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpExceptionResponse)) return false;
        HttpExceptionResponse that = (HttpExceptionResponse) o;
        return getStatus() == that.getStatus() &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getErrors(), that.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMessage(), getErrors());
    }
}
