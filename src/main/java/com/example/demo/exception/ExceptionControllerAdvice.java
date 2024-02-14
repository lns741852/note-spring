package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.*;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {




    /**
     * valid hanlder
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlerConstraintViolationException(Exception e) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
        String msg = StringUtils.collectionToCommaDelimitedString(
                constraintViolationException.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList()));

        log.error(msg, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.error(40001,"錯誤"));
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(Exception e) {
        StringBuilder message = new StringBuilder();
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                message.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(",");
            } else {
                message.append(objectError.getDefaultMessage()).append(",");
            }

        }
        log.error(message.toString(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.error(400,"錯誤"));
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequestException(Exception e) {
        BadRequestException exception = (BadRequestException) e;
        log.error(exception.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.error(exception.getCode(),exception.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handler(Exception e) {
        //if use @SneakyThrows throws Exceptio，, because @SneakyThrows can accept RuntimeException only.
        if(e instanceof UndeclaredThrowableException) {
            e = (Exception) e.getCause();
        }

        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.error(500,e.getMessage()));
    }





}