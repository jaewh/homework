package com.wemakeprice.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * 공통 ControllerAdvice
 *
 * @author rndlf104@gmail.com
 * @since 2022.06.11
 */
@ControllerAdvice
@Slf4j
public class BaseControllerAdviser {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> jsoupException(IOException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
