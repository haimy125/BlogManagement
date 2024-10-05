package com.myph.blogmanagement.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDTO {
    private String username;
    private String password;
}
