package com.kaze2.demo.blogger.api.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerResponse<T> {
    private String message;
    private T payload;
}
