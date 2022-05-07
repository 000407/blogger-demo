package com.kaze2.demo.blogger.mapping;

import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.model.BlogPostData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BlogPostDataMappingFunction implements Function<BlogPost, BlogPostData> {
    private final AuthorDataMappingFunction authorMappingFunction;

    @Override
    public BlogPostData apply(BlogPost blogPost) {
        final BlogPostData blogPostData = new BlogPostData();

        blogPostData.setId(blogPost.getId());
        blogPostData.setTitle(blogPost.getTitle());
        blogPostData.setBody(blogPost.getBody());
        blogPostData.setAuthor(authorMappingFunction.apply(blogPost.getAuthor()));

        return blogPostData;
    }
}
