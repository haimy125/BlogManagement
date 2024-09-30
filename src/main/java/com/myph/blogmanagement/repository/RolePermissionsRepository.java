package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.Permissions;
import com.myph.blogmanagement.model.RolePermissions;
import com.myph.blogmanagement.model.keys.RolePermissionsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionsRepository extends JpaRepository<RolePermissions, RolePermissionsKey> {
//    @Query("SELECT p FROM RolePermissions rp JOIN Permissions p ON rp.permissionId = p.permissionId "
//            + "WHERE rp.roleId IN (SELECT a.roleId FROM AccountsRoles a WHERE a.accountId = :accountId)")
    List<RolePermissions> findByRoles_Users_Accounts_AccountId(String accountId);
}
