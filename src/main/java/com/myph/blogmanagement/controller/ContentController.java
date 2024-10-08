package com.myph.blogmanagement.controller;

import com.myph.blogmanagement.payload.ResponseData;
import com.myph.blogmanagement.payload.request.ContentRequestDTO;
import com.myph.blogmanagement.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/create")
    public ResponseEntity<?> posts(@RequestBody ContentRequestDTO contentRequestDTO) {
        ResponseData responseData = new ResponseData();
        if (contentService.create(contentRequestDTO)) {
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setSuccess(true);
            responseData.setData("Content created successfully");
            responseData.setDesc("Content has been added to the system.");
            return ResponseEntity.ok(responseData);
        } else {
            responseData.setUserId(contentRequestDTO.getUserId());
            responseData.setStatus(HttpStatus.BAD_REQUEST.value());
            responseData.setSuccess(false);
            responseData.setDesc("Failed to create content. User might not exist or invalid data.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAllContent(@RequestParam(defaultValue = "0")int page,
                                           @RequestParam(defaultValue = "10") int size) {
        ResponseData responseData = new ResponseData();
        responseData.setData(contentService.getAllContent(page, size));
        responseData.setSuccess(true);
        responseData.setDesc("Dữ liệu trả về thành công!!!");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
