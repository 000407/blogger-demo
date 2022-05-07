package com.kaze2.demo.blogger.api.impl;

import com.kaze2.demo.blogger.api.BlogPostApi;
import com.kaze2.demo.blogger.api.constant.ServerResponseMessage;
import com.kaze2.demo.blogger.api.payload.*;
import com.kaze2.demo.blogger.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BlogPostController implements BlogPostApi {
    private final BlogPostService blogPostService;

    @Override
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<ServerResponse<List<BlogPost>>> getAllPosts(int offset, int limit) {
        final List<BlogPost> allPosts = blogPostService.getAllPosts(offset, limit);

        return ResponseEntity.ok(
                ServerResponse.<List<BlogPost>>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(allPosts)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<ServerResponse<BlogPost>> getPostById(long id) {
        final BlogPost blogPost = blogPostService.getPostById(id);

        return ResponseEntity.ok(
                ServerResponse.<BlogPost>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(blogPost)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<ServerResponse<List<Comment>>> getPostCommentsById(long id, int offset, int limit) {
        final List<Comment> comments = blogPostService.getPostCommentsById(id, offset, limit);

        return ResponseEntity.ok(
                ServerResponse.<List<Comment>>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(comments)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Map<String, Object>>> createNewPost(NewBlogPost newBlogPost) {
        final long postId = blogPostService.createNewPost(newBlogPost);

        final Map<String, Object> payload = new HashMap<>();
        payload.put("postId", postId);

        return ResponseEntity.ok(
                ServerResponse.<Map<String, Object>>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(payload)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<BlogPost>> updatePost(UpdatedBlogPost update) {
        final BlogPost blogPost = blogPostService.updatePost(update);

        return ResponseEntity.ok(
                ServerResponse.<BlogPost>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(blogPost)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Void>> deletePostById(long id) {
        blogPostService.deletePost(id);
        return ResponseEntity.ok(
                ServerResponse.<Void>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .build()
        );
    }
}
