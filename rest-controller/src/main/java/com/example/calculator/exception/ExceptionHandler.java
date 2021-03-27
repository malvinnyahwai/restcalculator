package com.example.calculator.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentMismatch(TypeMismatchException exception, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), 400, "Bad Request", exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMethodArgumentMismatch(MissingServletRequestParameterException exception, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), 400, "Bad Request", exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
