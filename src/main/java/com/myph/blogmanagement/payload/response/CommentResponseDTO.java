package com.myph.blogmanagement.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {
    private String userId;
    private String fullName;
    private String avatar;
    private String body;
    private String image;
}
