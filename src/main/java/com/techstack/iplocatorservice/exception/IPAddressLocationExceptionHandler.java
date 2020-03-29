package com.techstack.iplocatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Global exception handler prepares error response based on the various
 * exception handler class.
 *
 * @author Karthikeyan
 *
 */
@ControllerAdvice
public class IPAddressLocationExceptionHandler {

    /**
     * This method execute when the user given input is not as per specification.
     *
     * @param ex
     * @return ResponseEntity which contains List of ExceptionResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ExceptionResponse>> handleConstraint(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Function<String, ExceptionResponse> prepareErrorResponse =
                message -> ExceptionResponse.of(message, HttpStatus.BAD_REQUEST);

        List<ExceptionResponse> responses = constraintViolations.stream().map(ConstraintViolation::getMessage)
                .map(prepareErrorResponse)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responses);
    }

    /**
     * This method execute when the system behaves wrongly.
     *
     * @param ex
     * @return ResponseEntity which contains List of ExceptionResponse
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<List<ExceptionResponse>> handleConstraint(RuntimeException ex) {
        ExceptionResponse response = ExceptionResponse.of(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        List<ExceptionResponse> responses = Arrays.asList(response);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responses);
    }
}
