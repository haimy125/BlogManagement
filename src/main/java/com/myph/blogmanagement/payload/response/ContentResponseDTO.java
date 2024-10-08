package com.myph.blogmanagement.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ContentResponseDTO {
    private String contentId;
    private String userId;
    private String fullName;
    private String avatar;
    private String title;
    private String body;
    private List<CommentResponseDTO> commentResponseDTOList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
