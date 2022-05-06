package com.kaze2.demo.blogger.api;

import com.kaze2.demo.blogger.api.exception.NotImplementedException;
import com.kaze2.demo.blogger.api.payload.BlogPost;
import com.kaze2.demo.blogger.api.payload.ServerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1.0/posts")
public interface BlogPostApi {
    @RouterOperation(
            operation = @Operation(
                    description = "Retrieve all blog posts", operationId = "hello", tags = "persons",
                    responses = @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = BlogPost.class)))))
    @GetMapping("/all")
    default ResponseEntity<ServerResponse<List<BlogPost>>> getAllPosts() {
        throw new NotImplementedException();
    }
}
