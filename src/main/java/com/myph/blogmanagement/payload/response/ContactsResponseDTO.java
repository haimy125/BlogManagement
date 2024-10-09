package com.myph.blogmanagement.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactsResponseDTO {
    private String contactType;
    private String contactValue;
    private boolean isPrimary;
}
