package com.kaze2.demo.blogger.mapping;

import com.kaze2.demo.blogger.api.payload.Comment;
import com.kaze2.demo.blogger.model.CommentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CommentMappingFunction implements Function<CommentData, Comment> {
    @Override
    public Comment apply(CommentData commentData) {
        return Comment.builder()
                .id(commentData.getId())
                .name(commentData.getName())
                .email(commentData.getEmail())
                .body(commentData.getBody())
                .createdOn(commentData.getCreatedOn())
                .modifiedOn(commentData.getModifiedOn())
                .build();
    }
}
