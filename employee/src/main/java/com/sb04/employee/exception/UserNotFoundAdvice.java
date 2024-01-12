package com.sb04.employee.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.HashMap;
@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> exceptionHandler(UserNotFoundException ex){
        Map<String,String> errormap = new HashMap<>();
        errormap.put("errorMessage",ex.getMessage());

        return errormap;
    }


}
