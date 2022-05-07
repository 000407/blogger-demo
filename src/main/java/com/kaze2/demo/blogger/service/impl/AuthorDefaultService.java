package com.kaze2.demo.blogger.service.impl;

import com.kaze2.demo.blogger.api.payload.NewAuthor;
import com.kaze2.demo.blogger.model.AuthorData;
import com.kaze2.demo.blogger.model.repo.AuthorRepo;
import com.kaze2.demo.blogger.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorDefaultService implements AuthorService {
    private final AuthorRepo authorRepo;

    public long registerNewAuthor(NewAuthor newAuthor) {
        final AuthorData author = new AuthorData();

        author.setUsername(newAuthor.getUsername());
        author.setName(newAuthor.getName());
        author.setEmail(newAuthor.getEmail());
        author.setAddress(newAuthor.getAddress());

        return authorRepo.save(author)
                .getId();
    }
}
