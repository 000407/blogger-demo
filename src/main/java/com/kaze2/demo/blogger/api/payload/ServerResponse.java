package com.kaze2.demo.blogger.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> {
    private String message;
    private T payload;
}
