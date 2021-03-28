package com.example.calculator.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentMismatch(TypeMismatchException exception, HttpServletRequest httpServletRequest) {
        return badRequest(exception.getMessage(), httpServletRequest);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleMethodArgumentMismatch(ConstraintViolationException exception, HttpServletRequest httpServletRequest) {
        return badRequest(exception.getMessage(), httpServletRequest);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMethodArgumentMismatch(MissingServletRequestParameterException exception, HttpServletRequest httpServletRequest) {
        return badRequest(exception.getMessage(), httpServletRequest);
    }

    private ResponseEntity<?> badRequest(String message, HttpServletRequest httpServletRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), 400, "Bad Request", message, httpServletRequest.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
