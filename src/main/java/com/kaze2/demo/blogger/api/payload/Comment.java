package com.kaze2.demo.blogger.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private Long id;
    private String name;
    private String email;
    private String body;
}
