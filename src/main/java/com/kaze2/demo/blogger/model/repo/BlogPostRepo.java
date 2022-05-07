package com.kaze2.demo.blogger.model.repo;

import com.kaze2.demo.blogger.model.BlogPostData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPostData, Long>, SafeDelete<Long> {
}
