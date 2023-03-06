package com.banqmasr.platform.controllers;

import org.banqmasr.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = BusinessException.class)
/*
* used for  send well formed message in case Business exception fired
* */
    public ResponseEntity<Object> exception(BusinessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
}
