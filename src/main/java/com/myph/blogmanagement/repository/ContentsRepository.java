package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, String> {
}
