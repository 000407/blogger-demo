package com.kaze2.demo.blogger.service.impl;

import com.kaze2.demo.blogger.api.constant.ResourceType;
import com.kaze2.demo.blogger.api.exception.NotFoundException;
import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewComment;
import com.kaze2.demo.blogger.api.payload.UpdatedComment;
import com.kaze2.demo.blogger.mapping.CommentMappingFunction;
import com.kaze2.demo.blogger.model.BlogPostData;
import com.kaze2.demo.blogger.model.CommentData;
import com.kaze2.demo.blogger.model.repo.BlogPostRepo;
import com.kaze2.demo.blogger.model.repo.CommentRepo;
import com.kaze2.demo.blogger.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentDefaultService implements CommentService {
    private final BlogPostRepo blogPostRepo;
    private final CommentMappingFunction commentMappingFunction;
    private final CommentRepo commentRepo;

    @Override
    public Comment getCommentById(long id) {
        return commentRepo.findById(id)
                .map(commentMappingFunction)
                .orElseThrow(() -> new NotFoundException(ResourceType.COMMENT, id));
    }

    @Override
    public List<Comment> getPostCommentsById(long postId, int offset, int limit) {
        return commentRepo.findAllByBlogPost_Id(postId, PageRequest.of(offset, limit))
                .stream()
                .map(commentMappingFunction)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long createNewComment(NewComment newComment) {
        final BlogPostData blogPostData = blogPostRepo.findById(newComment.getPostId())
                .orElseThrow(() -> new NotFoundException(ResourceType.BLOG_POST, newComment.getPostId()));

        final CommentData comment = new CommentData();

        comment.setEmail(newComment.getEmail());
        comment.setName(newComment.getName());
        comment.setBlogPost(blogPostData);
        comment.setBody(newComment.getBody());

        return commentRepo.save(comment)
                .getId();
    }

    @Override
    @Transactional
    public Comment updateComment(UpdatedComment updatedComment) {
        final CommentData comment = commentRepo.findById(updatedComment.getId())
                .orElseThrow(() -> new NotFoundException(ResourceType.COMMENT, updatedComment.getId()));

        comment.setName(updatedComment.getName());
        comment.setBody(updatedComment.getBody());

        return commentMappingFunction.apply(commentRepo.save(comment));
    }

    @Override
    @Transactional
    public void deleteComment(long commentId) {
        commentRepo.safelyDeleteById(commentId);
    }
}
