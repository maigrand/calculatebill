package com.maigrand.calculatebill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

    public BadRequestException(CharSequence message) {
        super(HttpStatus.BAD_REQUEST, String.valueOf(message));
    }
}
