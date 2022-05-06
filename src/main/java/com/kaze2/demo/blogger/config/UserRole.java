package com.kaze2.demo.blogger.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserRole {
    public static final String READER = "READER";
    public static final String WRITER = "WRITER";
}
