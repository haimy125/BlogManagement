package com.myph.blogmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

    @Id
    @Column(name = "contact_id")
    private String contactId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "contact_value")
    private String contactValue;

    @Column(name = "is_primary")
    private boolean isPrimary;
}
