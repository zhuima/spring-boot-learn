package com.zhuima.springbootapi.resource;


import lombok.Data;

@Data
public class ErrorResource {
    private String message;

    public ErrorResource(String message) {
        this.message = message;
    }
}
