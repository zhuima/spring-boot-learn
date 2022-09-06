package com.zhuima.springbootapi.resource;

import lombok.Data;

@Data
public class FieldResource {
    private String resource;
    private String field;
    private String code;
    private String message;

    public FieldResource(String resource, String field, String code, String message) {
        this.resource = resource;
        this.field = field;
        this.code = code;
        this.message = message;
    }
}
