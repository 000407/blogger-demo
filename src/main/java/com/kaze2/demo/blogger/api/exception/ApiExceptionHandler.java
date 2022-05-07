package com.kaze2.demo.blogger.api.exception;

import com.kaze2.demo.blogger.api.payload.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ServerResponse<Void>> handle(NotImplementedException e) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(ServerResponse.<Void>builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ServerResponse<Void>> handle(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ServerResponse.<Void>builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ServerResponse<Void>> handle(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServerResponse.<Void>builder()
                        .message(e.getMessage())
                        .build());
    }
}
