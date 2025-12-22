package com.diego.cleanArch.core.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final String resourceType;
    private final Object identifier;

    public ResourceNotFoundException(String resourceType, Object identifier) {
        super(String.format("%s not found with identifier: %s", resourceType, identifier));
        this.resourceType = resourceType;
        this.identifier = identifier;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Object getIdentifier() {
        return identifier;
    }
}
