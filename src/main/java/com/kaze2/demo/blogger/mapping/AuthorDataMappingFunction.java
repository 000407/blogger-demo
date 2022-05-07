package com.kaze2.demo.blogger.mapping;

import com.kaze2.demo.blogger.api.payload.Author;
import com.kaze2.demo.blogger.model.AuthorData;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AuthorDataMappingFunction implements Function<Author, AuthorData> {
    @Override
    public AuthorData apply(Author author) {
        final AuthorData authorData = new AuthorData();

        authorData.setId(author.getId());
        authorData.setName(author.getName());
        authorData.setEmail(author.getEmail());
        authorData.setUsername(author.getUsername());

        return authorData;
    }
}
