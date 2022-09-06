package com.zhuima.springbootapi.resource;


import lombok.Data;

@Data
public class InvalidErrorResource {
    private String message;
    private Object errors;

    public InvalidErrorResource(String message, Object errors) {
        this.message = message;
        this.errors = errors;
    }
}
