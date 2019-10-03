package com.tsmc.filebrowser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadParameterRequestException extends RuntimeException {
    public BadParameterRequestException() {
        super();
    }
    public BadParameterRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public BadParameterRequestException(String message) {
        super(message);
    }
    public BadParameterRequestException(Throwable cause) {
        super(cause);
    }
}