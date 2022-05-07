package com.kaze2.demo.blogger.api.payload;

import lombok.Data;

@Data
public class UpdatedBlogPost {
    private long id;
    private String title;
    private String body;
}
