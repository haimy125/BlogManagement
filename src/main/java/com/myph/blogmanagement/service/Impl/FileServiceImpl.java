package com.myph.blogmanagement.service.Impl;

import com.myph.blogmanagement.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Value("${fileUpload.rootPath}")
    private String rootPath;

    private Path root;

    private void init(){
        try {
            root = Paths.get(rootPath);

            if (Files.notExists(root)) {
                Files.createDirectories(root);
            }
        } catch (Exception e) {
            System.err.println("Error create folder root: " + e.getMessage());
        }
    }

    /**
     * @param multipartFile
     * @return
     */
    @Override
    public boolean saveFile(MultipartFile multipartFile) {
        try {
            init();
            Files.copy(multipartFile.getInputStream(), root.resolve(multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            System.err.println("Error save file: " + e.getMessage());
            return false;
        }
    }

    /**
     * @param fileName
     * @return
     */
    @Override
    public Resource loadFile(String fileName) {
        init();
        Path file = root.resolve(fileName);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            System.err.println("Error load file: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
