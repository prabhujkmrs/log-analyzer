package com.test.prabhuj.exception.handler;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.test.prabhuj.exception.InternalServerErrorException;
import com.test.prabhuj.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;


import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HttpExceptionHandlerTest {

    private final FieldError fe1 = new FieldError("name1", "field1", "is empty");
    private final FieldError fe2 = new FieldError("name2", "field2", "is null");

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private HttpExceptionHandler handler;

    @Before
    public void setup() {
        initMocks(this);

        handler = new HttpExceptionHandler();
    }

    @Test
    public void testHandleNotFoundExceptionWithNoMessage() {
        ResponseEntity<HttpExceptionResponse> result = handler.handleNotFoundException(new NotFoundException());

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(result.getStatusCodeValue(), is(equalTo(404)));
        assertThat(result.getBody(), is(equalTo(new HttpExceptionResponse(404,"HTTP 404 Not Found"))));

    }

    @Test
    public void testHandleNotFoundExceptionWithMessage() {
        ResponseEntity<HttpExceptionResponse> result = handler.handleNotFoundException(new NotFoundException("Custom message"));

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(result.getStatusCodeValue(), is(equalTo(404)));
        assertThat(result.getBody(), is(equalTo(new HttpExceptionResponse(404,"Custom message"))));
    }

    @Test
    public void testHandleInternalServerErrorExceptionWithNoMessage() {
        ResponseEntity<HttpExceptionResponse> result = handler.handleInternalServerErrorException(new InternalServerErrorException());

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertThat(result.getStatusCodeValue(), is(equalTo(500)));
        assertThat(result.getBody(), is(equalTo(new HttpExceptionResponse(500,"HTTP 500 Internal Server Error"))));
    }

    @Test
    public void testHandleInternalServerErrorExceptionWithMessage() {
        ResponseEntity<HttpExceptionResponse> result = handler.handleInternalServerErrorException(new InternalServerErrorException("Custom message"));

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertThat(result.getStatusCodeValue(), is(equalTo(500)));
        assertThat(result.getBody(), is(equalTo(new HttpExceptionResponse(500,"Custom message"))));
    }

    @Test
    public void testHandleServletRequestBindingException() {
        ResponseEntity<HttpExceptionResponse> result = handler.handleBadRequestValidationErrors(new ServletRequestBindingException("Custom message"));

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST)));
        assertThat(result.getStatusCodeValue(), is(equalTo(400)));
        assertThat(result.getBody(), is(equalTo(new HttpExceptionResponse(400,"Bad Request",Collections.singletonList("Custom message")))));
    }
}
