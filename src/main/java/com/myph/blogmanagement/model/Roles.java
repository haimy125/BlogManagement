package com.myph.blogmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<RolePermissions> rolePermissions;

    @OneToMany(mappedBy = "roles")
    private Set<UserRoles> userRoles;
}
