package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, String> {
}
