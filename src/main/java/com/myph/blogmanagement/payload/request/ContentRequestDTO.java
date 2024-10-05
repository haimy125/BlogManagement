package com.myph.blogmanagement.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentRequestDTO {
    private String userId;
    private String title;
    private String body;
}
