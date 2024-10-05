package com.myph.blogmanagement.service;

import com.myph.blogmanagement.payload.request.ContentRequestDTO;

public interface ContentService {
    boolean create(ContentRequestDTO contentRequestDTO);
}
