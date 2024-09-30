package com.myph.blogmanagement.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
}
