package com.kaze2.demo.blogger.model.repo;

import com.kaze2.demo.blogger.model.CommentData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<CommentData, Long>, SafeDelete<Long> {
    List<CommentData> findAllByBlogPost_Id(Long postId, Pageable pageable);
}
