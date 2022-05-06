package com.kaze2.demo.blogger.api.exception;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("This operation is not implemented");
    }
}
