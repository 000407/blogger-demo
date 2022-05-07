package com.kaze2.demo.blogger.api.payload;

import lombok.Data;

@Data
public class UpdatedAuthor {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String address;
}
