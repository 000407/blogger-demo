package com.kaze2.demo.blogger.api.payload;

import lombok.Data;

@Data
public class NewBlogPost {
    private String title;
    private String body;
    private Long authorId;
}
