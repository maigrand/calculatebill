package com.maigrand.calculatebill.exception;

public class EntityExistsException extends BadRequestException {

    public EntityExistsException(CharSequence message) {
        super(message);
    }
}
