package com.example.demo.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private Integer errorCode = 400;
    private LocalDateTime  timestamp;
    private String message;

    private ErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public static ErrorResponse error(String message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        return errorResponse;
    }

    public static ErrorResponse error(Integer status, String message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(status);
        errorResponse.setMessage(message);
        return errorResponse;
    }


}
