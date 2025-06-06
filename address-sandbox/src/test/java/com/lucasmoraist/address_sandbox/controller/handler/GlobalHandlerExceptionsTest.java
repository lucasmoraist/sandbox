package com.lucasmoraist.address_sandbox.controller.handler;

import com.lucasmoraist.address_sandbox.exception.ExceptionDto;
import com.lucasmoraist.address_sandbox.exception.ViaCepException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalHandlerExceptionsTest {

    private final GlobalHandlerExceptions handler = new GlobalHandlerExceptions();

    @Test
    @DisplayName("Should return NOT_FOUND for ViaCepException")
    void case01() {
        ViaCepException ex = new ViaCepException("cep inválido");
        ResponseEntity<ExceptionDto> response = handler.handleViaCepException(ex);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("cep inválido", response.getBody().message());
        assertEquals(HttpStatus.NOT_FOUND, response.getBody().status());
    }

    @Test
    @DisplayName("Should return INTERNAL_SERVER_ERROR for generic Exception")
    void case02() {
        Exception ex = new Exception("erro inesperado");
        ResponseEntity<ExceptionDto> response = handler.handleException(ex);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("erro inesperado", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getBody().status());
    }

}