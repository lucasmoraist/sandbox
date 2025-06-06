package com.lucasmoraist.address_sandbox.controller.handler;

import com.lucasmoraist.address_sandbox.exception.ExceptionDto;
import com.lucasmoraist.address_sandbox.exception.ViaCepException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(ViaCepException.class)
    protected ResponseEntity<ExceptionDto> handleViaCepException(ViaCepException e) {
        return ResponseEntity.ok()
                .body(new ExceptionDto(
                        e.getMessage(),
                        HttpStatus.NOT_FOUND,
                        e
                ));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> handleException(Exception e) {
        return ResponseEntity.ok()
                .body(new ExceptionDto(
                        e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        e
                ));
    }

}
