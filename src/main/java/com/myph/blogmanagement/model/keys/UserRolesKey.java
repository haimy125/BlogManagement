package com.myph.blogmanagement.model.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRolesKey implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;
}
