package com.example.cursorestfulspringboot.controllers.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Captura excessões
@RestControllerAdvice
public class MyHandleExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        //Guarda as mensagens de erro dentro do Map
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String field = ((FieldError) error).getField();
            String code = ((FieldError) error).getCode();
            String msg = error.getDefaultMessage();
            errors.put("Error: " + code + ":" + field,msg);
        });

        return errors;
    }
}
