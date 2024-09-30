package com.myph.blogmanagement.model;

import com.myph.blogmanagement.model.keys.UserRolesKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userroles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles {

    @EmbeddedId
    private UserRolesKey userRolesKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Roles roles;
}
