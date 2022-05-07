package com.kaze2.demo.blogger.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogPost {
    @NotNull
    private Long id;
    private String title;
    private String body;
    private Author author;
    private List<Comment> comments;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
