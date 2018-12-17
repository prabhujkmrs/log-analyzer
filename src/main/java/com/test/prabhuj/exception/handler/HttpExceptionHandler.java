package com.test.prabhuj.exception.handler;

import com.test.prabhuj.exception.InternalServerErrorException;
import com.test.prabhuj.exception.InvocationTargetException;
import com.test.prabhuj.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Collections;

import static org.springframework.http.HttpStatus.*;

/**
 * Controller advice that will handle all defined exceptions and returned the relevant {@link HttpExceptionResponse}
 * in the HTTP response.
 */
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new HttpExceptionResponse(NOT_FOUND.value(),ex.getMessage()));
    }

    @ExceptionHandler(InvocationTargetException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleNotFoundException(InvocationTargetException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new HttpExceptionResponse(BAD_REQUEST.value(),ex.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleInternalServerErrorException(InternalServerErrorException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new HttpExceptionResponse(INTERNAL_SERVER_ERROR.value(),ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleBadRequestValidationErrors(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new HttpExceptionResponse(BAD_REQUEST.value(),BAD_REQUEST.getReasonPhrase()));
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleBadRequestValidationErrors(ServletRequestBindingException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new HttpExceptionResponse(BAD_REQUEST.value(),BAD_REQUEST.getReasonPhrase(),Collections.singletonList(ex.getMessage())));
    }

}
