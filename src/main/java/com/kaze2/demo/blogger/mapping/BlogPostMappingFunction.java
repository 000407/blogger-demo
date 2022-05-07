package com.kaze2.demo.blogger.mapping;

import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.model.BlogPostData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BlogPostMappingFunction implements Function<BlogPostData, BlogPost> {
    private final AuthorMappingFunction authorMappingFunction;

    @Override
    public BlogPost apply(BlogPostData blogPostData) {
        return BlogPost.builder()
                .id(blogPostData.getId())
                .title(blogPostData.getTitle())
                .body(blogPostData.getBody())
                .author(authorMappingFunction.apply(blogPostData.getAuthor()))
                .createdOn(blogPostData.getCreatedOn())
                .modifiedOn(blogPostData.getModifiedOn())
                .build();
    }
}
