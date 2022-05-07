package com.kaze2.demo.blogger.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private Long id;
    private String name;
    private String email;
    private Long postId;
    private String body;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
