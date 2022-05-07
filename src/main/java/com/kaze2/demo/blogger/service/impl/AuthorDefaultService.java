package com.kaze2.demo.blogger.service.impl;

import com.kaze2.demo.blogger.api.constant.ResourceType;
import com.kaze2.demo.blogger.api.exception.NotFoundException;
import com.kaze2.demo.blogger.api.payload.Author;
import com.kaze2.demo.blogger.api.payload.NewAuthor;
import com.kaze2.demo.blogger.api.payload.UpdatedAuthor;
import com.kaze2.demo.blogger.mapping.AuthorMappingFunction;
import com.kaze2.demo.blogger.model.AuthorData;
import com.kaze2.demo.blogger.model.repo.AuthorRepo;
import com.kaze2.demo.blogger.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorDefaultService implements AuthorService {
    private final AuthorRepo authorRepo;
    private final AuthorMappingFunction authorMappingFunction;

    public long registerNewAuthor(NewAuthor newAuthor) {
        final AuthorData author = new AuthorData();

        author.setUsername(newAuthor.getUsername());
        author.setName(newAuthor.getName());
        author.setEmail(newAuthor.getEmail());
        author.setAddress(newAuthor.getAddress());

        return authorRepo.save(author)
                .getId();
    }

    @Override
    public List<Author> getAllAuthors(int offset, int limit) {
        return authorRepo.findAll(PageRequest.of(offset, limit))
                .stream()
                .map(authorMappingFunction)
                .collect(Collectors.toList());
    }

    @Override
    public Author getAuthorById(long id) {
        return authorRepo.findById(id)
                .map(authorMappingFunction)
                .orElseThrow(() -> new NotFoundException(ResourceType.AUTHOR, id));
    }

    @Override
    @Transactional
    public Author updateAuthorInfo(UpdatedAuthor update) {
        final AuthorData author = authorRepo.findById(update.getId())
                .orElseThrow(() -> new NotFoundException(ResourceType.AUTHOR, update.getId()));

        author.setUsername(update.getUsername());
        author.setName(update.getName());
        author.setEmail(update.getEmail());
        author.setAddress(update.getAddress());

        return authorMappingFunction.apply(authorRepo.save(author));
    }

    @Override
    @Transactional
    public void deleteAuthorById(Long id) {
        //TODO: Consider using a soft-delete instead of a hard deletion
        authorRepo.safelyDeleteById(id);
    }
}
