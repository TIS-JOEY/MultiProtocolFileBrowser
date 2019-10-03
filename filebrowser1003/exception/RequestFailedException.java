package com.tsmc.filebrowser.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RequestFailedException extends RuntimeException {
    public RequestFailedException() {
        super();
    }
    public RequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }
    public RequestFailedException(String message) {
        super(message);
    }
    public RequestFailedException(Throwable cause) {
        super(cause);
    }
}