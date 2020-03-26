package com.techstack.iplocatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

/**
 * Global exception handler prepares error response based on the various
 * exception handler class.
 *
 * @author Karthikeyan N
 *
 */
@ControllerAdvice
public class IPAddressLocationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraint(ConstraintViolationException ex) {
        String errorMessage = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();
        ExceptionResponse response = ExceptionResponse.of(errorMessage, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
