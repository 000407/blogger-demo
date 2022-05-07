package com.kaze2.demo.blogger.model.repo;

import com.kaze2.demo.blogger.model.AuthorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<AuthorData, Long> {
}
