package com.kaze2.demo.blogger.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String address;
    private List<BlogPost> blogPosts;
}
