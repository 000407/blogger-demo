package com.kaze2.demo.blogger.service;

import com.kaze2.demo.blogger.api.payload.Author;
import com.kaze2.demo.blogger.api.payload.NewAuthor;
import com.kaze2.demo.blogger.api.payload.UpdatedAuthor;

import java.util.List;

public interface AuthorService {
    long registerNewAuthor(NewAuthor newAuthor);

    Author getAuthorById(long id);

    Author updateAuthorInfo(UpdatedAuthor update);

    void deleteAuthorById(Long id);

    List<Author> getAllAuthors(int offset, int limit);
}
