package com.kaitait.registration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity IllegalArgumentExceptionErrorHandler(final HttpServletRequest req, final IllegalArgumentException e) {
        log.error("IllegalArgumentException some arguments were invalid or missing: " + req.getRequestURI(), e);
        return new ResponseEntity("Something unexpected happened", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity RuntimeExceptionErrorHandler(final HttpServletRequest req, final RuntimeException e) {
        log.error("Something unexpected happened: " + req.getRequestURI(), e);
        return new ResponseEntity("Something unexpected happened", HttpStatus.BAD_REQUEST);
    }
}
