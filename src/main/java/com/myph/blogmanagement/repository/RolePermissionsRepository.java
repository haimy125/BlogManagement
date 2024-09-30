package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.RolePermissions;
import com.myph.blogmanagement.model.keys.RolePermissionsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolePermissionsRepository extends JpaRepository<RolePermissions, RolePermissionsKey> {
}
