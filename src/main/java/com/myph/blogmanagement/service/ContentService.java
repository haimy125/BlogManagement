package com.myph.blogmanagement.service;

import com.myph.blogmanagement.model.Contents;
import com.myph.blogmanagement.payload.request.ContentRequestDTO;
import com.myph.blogmanagement.payload.response.ContentResponseDTO;

import java.util.List;


public interface ContentService {
    boolean create(ContentRequestDTO contentRequestDTO);
    List<ContentResponseDTO> getAllContent(int page, int size);
    ContentResponseDTO getContent(String id);
}
