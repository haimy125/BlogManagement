package com.myph.blogmanagement.payload.request;

import com.myph.blogmanagement.payload.UserBaseDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequestDTO extends UserBaseDTO {
    private String password;
}
