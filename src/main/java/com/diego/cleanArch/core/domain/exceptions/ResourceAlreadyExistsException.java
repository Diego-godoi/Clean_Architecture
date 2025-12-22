package com.diego.cleanArch.core.domain.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    private final String resourceType;
    private final String conflictField;
    private final Object conflictValue;

    public ResourceAlreadyExistsException(String resourceType, String conflictField, Object conflictValue) {
        super(String.format("%s already exists with %s: %s", resourceType, conflictField, conflictValue));
        this.resourceType = resourceType;
        this.conflictField = conflictField;
        this.conflictValue = conflictValue;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getConflictField() {
        return conflictField;
    }

    public Object getConflictValue() {
        return conflictValue;
    }
}
