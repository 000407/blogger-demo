package com.kaze2.demo.blogger.api.impl;

import com.kaze2.demo.blogger.api.AuthorApi;
import com.kaze2.demo.blogger.api.constant.ServerResponseMessage;
import com.kaze2.demo.blogger.api.payload.*;
import com.kaze2.demo.blogger.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {
    private final AuthorService authorService;

    @Override
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
}
