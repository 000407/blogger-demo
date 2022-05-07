package com.kaze2.demo.blogger.api.impl;

import com.kaze2.demo.blogger.api.AuthorApi;
import com.kaze2.demo.blogger.api.constant.ServerResponseMessage;
import com.kaze2.demo.blogger.api.payload.*;
import com.kaze2.demo.blogger.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {
    private final AuthorService authorService;

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Map<String, Object>>> registerNewAuthor(NewAuthor newAuthor) {
        final long authorId = authorService.registerNewAuthor(newAuthor);

        final Map<String, Object> payload = new HashMap<>();
        payload.put("authorId", authorId);

        return ResponseEntity.ok(
                ServerResponse.<Map<String, Object>>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(payload)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<ServerResponse<List<Author>>> getAllAuthors(int offset, int limit) {
        List<Author> authors = authorService.getAllAuthors(offset, limit);

        return ResponseEntity.ok(
                ServerResponse.<List<Author>>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(authors)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<ServerResponse<Author>> getAuthorById(long id) {
        final Author author = authorService.getAuthorById(id);

        return ResponseEntity.ok(
                ServerResponse.<Author>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(author)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Author>> updateAuthorDetails(UpdatedAuthor update) {
        final Author updatedAuthor = authorService.updateAuthorInfo(update);

        return ResponseEntity.ok(
                ServerResponse.<Author>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(updatedAuthor)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Void>> deleteAuthorDetails(Long id) {
        authorService.deleteAuthorById(id);

        return ResponseEntity.ok(
                ServerResponse.<Void>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .build()
        );
    }
}
