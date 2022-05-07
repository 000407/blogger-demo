package com.kaze2.demo.blogger.service.impl;

import com.kaze2.demo.blogger.api.constant.ResourceType;
import com.kaze2.demo.blogger.api.exception.NotFoundException;
import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewBlogPost;
import com.kaze2.demo.blogger.api.payload.UpdatedBlogPost;
import com.kaze2.demo.blogger.mapping.BlogPostMappingFunction;
import com.kaze2.demo.blogger.model.AuthorData;
import com.kaze2.demo.blogger.model.BlogPostData;
import com.kaze2.demo.blogger.model.repo.AuthorRepo;
import com.kaze2.demo.blogger.model.repo.BlogPostRepo;
import com.kaze2.demo.blogger.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BlogPostDefaultService implements BlogPostService {
    private final AuthorRepo authorRepo;
    private final BlogPostRepo blogPostRepo;
    private final BlogPostMappingFunction blogPostMappingFunction;

    @Override
    public List<BlogPost> getAllPosts(int offset, int limit) {
        return blogPostRepo.findAll(PageRequest.of(offset, limit))
                .stream()
                .map(blogPostMappingFunction)
                .collect(Collectors.toList());
    }

    @Override
    public BlogPost getPostById(long id) {
        return blogPostRepo.findById(id)
                .map(blogPostMappingFunction).orElseThrow(() -> new NotFoundException(ResourceType.BLOG_POST, id));
    }

    @Override
    public List<Comment> getPostCommentsById(long id, int offset, int limit) {
        return blogPostRepo.findById(id)
                .map(blogPostMappingFunction)
                .map(BlogPost::getComments)
                .orElseThrow(() -> new NotFoundException(ResourceType.COMMENT, id));
    }

    @Override
    public Long createNewPost(NewBlogPost newPost) {
        final AuthorData authorData = authorRepo.findById(newPost.getAuthorId())
                .orElseThrow(() -> new NotFoundException(ResourceType.AUTHOR, newPost.getAuthorId()));

        final BlogPostData blogPostData = new BlogPostData();

        blogPostData.setTitle(newPost.getTitle());
        blogPostData.setBody(newPost.getBody());
        blogPostData.setAuthor(authorData);

        return blogPostRepo.save(blogPostData)
                .getId();
    }

    @Override
    public Long updatePost(UpdatedBlogPost updatedBlogPost) {
        final BlogPostData blogPostData = blogPostRepo.findById(updatedBlogPost.getId())
                .orElseThrow(() -> new NotFoundException(ResourceType.BLOG_POST, updatedBlogPost.getId()));

        blogPostData.setTitle(updatedBlogPost.getTitle());
        blogPostData.setBody(updatedBlogPost.getBody());

        return blogPostRepo.save(blogPostData)
                .getId();
    }

    @Override
    public Long deletePost(long postId) {
        blogPostRepo.deleteById(postId);
        return postId;
    }
}
