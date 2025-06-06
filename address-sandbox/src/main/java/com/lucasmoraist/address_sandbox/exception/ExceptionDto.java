package com.lucasmoraist.address_sandbox.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDto(
        String message,
        HttpStatus status,
        Throwable e
) {
}
