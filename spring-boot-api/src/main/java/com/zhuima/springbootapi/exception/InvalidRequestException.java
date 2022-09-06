package com.zhuima.springbootapi.exception;

import lombok.Data;
import org.springframework.validation.Errors;

@Data
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }
}
