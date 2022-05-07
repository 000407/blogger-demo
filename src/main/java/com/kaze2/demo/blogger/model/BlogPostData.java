package com.kaze2.demo.blogger.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "blog_posts")
@EqualsAndHashCode(callSuper = true)
public class BlogPostData extends Audited {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @OneToMany(
            mappedBy = "blogPost",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CommentData> comments;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorData author;
}
