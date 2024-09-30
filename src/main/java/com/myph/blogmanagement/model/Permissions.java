package com.myph.blogmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permissions {

    @Id
    @Column(name = "permission_id")
    private String permissionId;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permissions")
    private Set<RolePermissions> rolePermissions;
}
