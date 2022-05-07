package com.kaze2.demo.blogger.api.payload;

import lombok.Data;

@Data
public class UpdatedComment {
    private Long id;
    private String name;
    private String body;
}
