package com.techstack.iplocatorservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * This ExceptionResponse prepares whenever system behaves wrongly and the
 * same will be send back to the caller.
 *
 * @author Karthikeyan N
 */
@AllArgsConstructor
@Getter
public class ExceptionResponse {

    private final HttpStatus status;
    private final int statusCode;
    private final String message;
    private final LocalDateTime localDateTime;

    public static ExceptionResponse of(final String message, HttpStatus status) {
        return new ExceptionResponse(status, status.value(), message, LocalDateTime.now());
    }

}
