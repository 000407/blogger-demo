package com.kaze2.demo.blogger.api.payload;

import lombok.Data;

@Data
public class NewComment {
    private String name;
    private String email;
    private Long postId;
    private String body;
}
