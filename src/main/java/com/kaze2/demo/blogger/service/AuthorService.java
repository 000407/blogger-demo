package com.kaze2.demo.blogger.service;

import com.kaze2.demo.blogger.api.payload.NewAuthor;

public interface AuthorService {
    long registerNewAuthor(NewAuthor newAuthor);
}
