package com.myph.blogmanagement.model;

import com.myph.blogmanagement.model.keys.RolePermissionsKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RolePermissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissions {

    @EmbeddedId
    private RolePermissionsKey rolePermissionsKey;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Roles roles;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private Permissions permissions;

}
