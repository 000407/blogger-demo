package com.kaze2.demo.blogger.api;

import com.kaze2.demo.blogger.api.exception.NotImplementedException;
import com.kaze2.demo.blogger.api.payload.Author;
import com.kaze2.demo.blogger.api.payload.NewAuthor;
import com.kaze2.demo.blogger.api.payload.ServerResponse;
import com.kaze2.demo.blogger.api.payload.UpdatedAuthor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/v1.0/authors")
public interface AuthorApi {
    @PostMapping("/new")
    default ResponseEntity<ServerResponse<Map<String, Object>>> registerNewAuthor(@RequestBody NewAuthor newAuthor) {
        throw new NotImplementedException();
    }

    @GetMapping("/all")
    default ResponseEntity<ServerResponse<List<Author>>> getAllAuthors(@RequestParam int offset,
                                                                       @RequestParam int limit) {
        throw new NotImplementedException();
    }

    @GetMapping("/where/id/{id}")
    default ResponseEntity<ServerResponse<Author>> getAuthorById(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @PutMapping("/update")
    default ResponseEntity<ServerResponse<Author>> updateAuthorDetails(@RequestBody UpdatedAuthor update) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/where/id/{id}")
    default ResponseEntity<ServerResponse<Void>> deleteAuthorDetails(@PathVariable Long id) {
        throw new NotImplementedException();
    }
}
