package com.example.homewordexception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ConfigException {
    @ExceptionHandler(value = InvalidParamException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, Object> methodNotSupport(InvalidParamException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "000xx");
        map.put("message", ex.getMessage());
        map.put("timestamp", new Date());
        return map;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, Object> methodNotSupport(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("message", ex.getMessage());
        map.put("timestamp", new Date());
        return map;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequest(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "002");
        map.put("message", ex.getMessage());
        map.put("timestamp", new Date());
        return map;
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> conflict(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "003");
        map.put("message", ex.getMessage());
        map.put("timestamp", new Date());
        return map;
    }
}
