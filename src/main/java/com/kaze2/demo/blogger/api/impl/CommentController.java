package com.kaze2.demo.blogger.api.impl;

import com.kaze2.demo.blogger.api.CommentApi;
import com.kaze2.demo.blogger.api.constant.ServerResponseMessage;
import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.api.payload.NewComment;
import com.kaze2.demo.blogger.api.payload.ServerResponse;
import com.kaze2.demo.blogger.api.payload.UpdatedComment;
import com.kaze2.demo.blogger.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {
    private final CommentService commentService;

    @Override
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<ServerResponse<Comment>> getCommentById(long id) {
        final Comment comment = commentService.getCommentById(id);

        return ResponseEntity.ok(
                ServerResponse.<Comment>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(comment)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Map<String, Object>>> createNewComment(NewComment newComment) {
        final long commentId = commentService.createNewComment(newComment);

        final Map<String, Object> payload = new HashMap<>();
        payload.put("commentId", commentId);

        return ResponseEntity.ok(
                ServerResponse.<Map<String, Object>>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(payload)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Comment>> updateComment(UpdatedComment updatedComment) {
        final Comment comment = commentService.updateComment(updatedComment);

        return ResponseEntity.ok(
                ServerResponse.<Comment>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .payload(comment)
                        .build()
        );
    }

    @Override
    @PreAuthorize("hasRole('ROLE_WRITER')")
    public ResponseEntity<ServerResponse<Void>> deleteCommentById(long id) {
        commentService.deleteComment(id);

        return ResponseEntity.ok(
                ServerResponse.<Void>builder()
                        .message(ServerResponseMessage.SUCCESS)
                        .build()
        );
    }
}
