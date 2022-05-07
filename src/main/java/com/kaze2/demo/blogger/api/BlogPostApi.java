package com.kaze2.demo.blogger.api;

import com.kaze2.demo.blogger.api.exception.NotImplementedException;
import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewBlogPost;
import com.kaze2.demo.blogger.api.payload.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/v1.0/posts")
public interface BlogPostApi {
    @GetMapping("/all")
    default ResponseEntity<ServerResponse<List<BlogPost>>> getAllPosts(@RequestParam int offset,
                                                                       @RequestParam int limit) {
        throw new NotImplementedException();
    }

    @GetMapping("/where/id/{id}")
    default ResponseEntity<ServerResponse<BlogPost>> getPostById(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @GetMapping("/where/id/{id}/comments")
    default ResponseEntity<ServerResponse<List<Comment>>> getPostCommentsById(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @PostMapping("/new")
    default ResponseEntity<ServerResponse<Map<String, Object>>> createNewPost(@RequestBody NewBlogPost newBlogPost) {
        throw new NotImplementedException();
    }

    @PutMapping("/update/{id}")
    default ResponseEntity<ServerResponse<Long>> updatePost(@PathVariable long id,
                                                            @RequestBody BlogPost blogPost) {
        throw new NotImplementedException();
    }
}
