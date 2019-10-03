package com.tsmc.filebrowser.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoteStorageIOException extends RuntimeException {
    public RemoteStorageIOException() {
        super();
    }
    public RemoteStorageIOException(String message, Throwable cause) {
        super(message, cause);
    }
    public RemoteStorageIOException(String message) {
        super(message);
    }
    public RemoteStorageIOException(Throwable cause) {
        super(cause);
    }
}
