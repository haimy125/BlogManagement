package com.myph.blogmanagement.payload;

import com.myph.blogmanagement.payload.response.ContactsResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public abstract class UserBaseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String sex;
    private String avatar;
    private LocalDate dateOfBirth;
    private String address;
    private String bio;
    private Set<ContactsResponseDTO> contactsResponseDTOSet;
}
