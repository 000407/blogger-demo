package com.kaze2.demo.blogger.api.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceType {
    public static final String BLOG_POST = "BLOG_POST";
    public static final String AUTHOR = "AUTHOR";
    public static final String COMMENT = "COMMENT";
}
