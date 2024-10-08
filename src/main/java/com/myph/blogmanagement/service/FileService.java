package com.myph.blogmanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean saveFile(MultipartFile multipartFile);
    Resource loadFile(String fileName);
}
