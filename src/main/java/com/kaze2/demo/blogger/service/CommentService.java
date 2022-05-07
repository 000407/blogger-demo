package com.kaze2.demo.blogger.service;

import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewComment;
import com.kaze2.demo.blogger.api.payload.UpdatedComment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(long id);

    List<Comment> getPostCommentsById(long postId, int offset, int limit);

    Long createNewComment(NewComment newComment);

    Comment updateComment(UpdatedComment updatedComment);

    void deleteComment(long commentId);
}
