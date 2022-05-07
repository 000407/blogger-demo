package com.kaze2.demo.blogger.api;

import com.kaze2.demo.blogger.api.exception.NotImplementedException;
import com.kaze2.demo.blogger.api.payload.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1.0/comments")
public interface CommentApi {
    @GetMapping("/where/id/{id}")
    default ResponseEntity<ServerResponse<Comment>> getCommentById(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @PostMapping("/new")
    default ResponseEntity<ServerResponse<Map<String, Object>>> createNewComment(@RequestBody NewComment newComment) {
        throw new NotImplementedException();
    }

    @PutMapping("/update")
    default ResponseEntity<ServerResponse<Comment>> updateComment(@RequestBody UpdatedComment updatedComment) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/where/id/{id}")
    default ResponseEntity<ServerResponse<Void>> deleteCommentById(@PathVariable long id) {
        throw new NotImplementedException();
    }
}
