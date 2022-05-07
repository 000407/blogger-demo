package com.kaze2.demo.blogger.model.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SafeDelete<I> {
    @Modifying
    @Query("DELETE FROM #{#entityName} t WHERE t.id = ?1")
    void safelyDeleteById(I id);
}
