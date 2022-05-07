package com.kaze2.demo.blogger.service;

import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewBlogPost;
import com.kaze2.demo.blogger.api.payload.UpdatedBlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> getAllPosts(int offset, int limit);

    BlogPost getPostById(long id);

    List<Comment> getPostCommentsById(long id, int offset, int limit);

    Long createNewPost(NewBlogPost newPost);

    Long updatePost(UpdatedBlogPost updatedBlogPost);

    Long deletePost(long postId);
}
