package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.UserRoles;
import com.myph.blogmanagement.model.keys.UserRolesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesKey> {
}
