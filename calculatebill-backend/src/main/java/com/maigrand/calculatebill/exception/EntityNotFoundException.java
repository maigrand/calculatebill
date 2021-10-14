package com.maigrand.calculatebill.exception;

public class EntityNotFoundException extends BadRequestException {

    public EntityNotFoundException(CharSequence message) {
        super(message);
    }
}
