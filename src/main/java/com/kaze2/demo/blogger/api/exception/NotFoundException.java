package com.kaze2.demo.blogger.api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resourceName, Object id) {
        super("No " + resourceName + " resource found for identity: " + id.toString());
    }
}
