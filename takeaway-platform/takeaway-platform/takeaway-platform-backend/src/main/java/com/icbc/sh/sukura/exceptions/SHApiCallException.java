package com.icbc.sh.sukura.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class SHApiCallException extends RuntimeException{
    private String message;

    public SHApiCallException(String message){
        super(message);
        this.message = message;
    }
}
