package com.ex.novatech.bankinc.controller;

import com.ex.novatech.bankinc.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleBadRequestExceptions(HttpServletRequest request, Exception ex) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), request.getServletPath());
        error.addError(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        return error;
    }

    @ExceptionHandler({
            NoSuchElementException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleNotFoundExceptions(HttpServletRequest request, Exception ex) {
        ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND.value(), request.getServletPath());
        error.addError(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        return error;
    }
}
