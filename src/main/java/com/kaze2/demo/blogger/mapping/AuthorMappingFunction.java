package com.kaze2.demo.blogger.mapping;

import com.kaze2.demo.blogger.api.payload.Author;
import com.kaze2.demo.blogger.model.AuthorData;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AuthorMappingFunction implements Function<AuthorData, Author> {
    @Override
    public Author apply(AuthorData authorData) {
        return Author.builder()
                .id(authorData.getId())
                .name(authorData.getName())
                .username(authorData.getUsername())
                .email(authorData.getEmail())
                .address(authorData.getAddress())
                .build();
    }
}
