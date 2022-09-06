package com.zhuima.springbootapi.handler;


import com.zhuima.springbootapi.exception.InvalidRequestException;
import com.zhuima.springbootapi.exception.NotFoundException;
import com.zhuima.springbootapi.resource.ErrorResource;
import com.zhuima.springbootapi.resource.FieldResource;
import com.zhuima.springbootapi.resource.InvalidErrorResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 处理404的场景
     * @param exception
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleNotFound(RuntimeException exception) {
        ErrorResource errorResource = new ErrorResource(exception.getMessage());
        return new ResponseEntity<Object>(errorResource, HttpStatus.NOT_FOUND);
    }


    /**
     *
     * 处理参数验证失败
     * @param exception
     * @return
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<Object> handleInvalidRequest(InvalidRequestException exception) {
        Errors errors = exception.getErrors();
        List<FieldResource> filedResources = new ArrayList<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            FieldResource fieldResource = new FieldResource(fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage());
            filedResources.add(fieldResource);
        }
        InvalidErrorResource ire = new InvalidErrorResource(exception.getMessage(), filedResources);
        return new ResponseEntity<Object>(ire, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception exception){
        return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
