package com.diego.cleanArch.core.domain.exceptions;

public class InvalidDomainDataException extends RuntimeException {
    public final String field;

    public InvalidDomainDataException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
