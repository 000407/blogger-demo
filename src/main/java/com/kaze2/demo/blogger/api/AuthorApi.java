package com.kaze2.demo.blogger.api;

import com.kaze2.demo.blogger.api.exception.NotImplementedException;
import com.kaze2.demo.blogger.api.payload.NewAuthor;
import com.kaze2.demo.blogger.api.payload.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1.0/authors")
public interface AuthorApi {
    @PostMapping("/new")
    default ResponseEntity<ServerResponse<Map<String, Object>>> registerNewAuthor(@RequestBody NewAuthor newAuthor) {
        throw new NotImplementedException();
    }
}
