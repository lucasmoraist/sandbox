package com.lucasmoraist.address_sandbox.exception;

public class ViaCepException extends RuntimeException {
    public ViaCepException(String message, Throwable e) {
        super(message, e);
    }
    public ViaCepException(String message) {
        super(message);
    }

}
