package com.kaze2.demo.blogger.api.impl;

import com.kaze2.demo.blogger.api.BlogPostApi;
import com.kaze2.demo.blogger.api.constant.ServerResponseMessage;
import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewBlogPost;
import com.kaze2.demo.blogger.api.payload.ServerResponse;
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
    public ResponseEntity<ServerResponse<BlogPost>> getPostById(long id) {
        return BlogPostApi.super.getPostById(id);
    }

    @Override
    public ResponseEntity<ServerResponse<List<Comment>>> getPostCommentsById(long id) {
        return BlogPostApi.super.getPostCommentsById(id);
    }

    @Override
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
    public ResponseEntity<ServerResponse<Long>> updatePost(long id, BlogPost blogPost) {
        return BlogPostApi.super.updatePost(id, blogPost);
    }
}
