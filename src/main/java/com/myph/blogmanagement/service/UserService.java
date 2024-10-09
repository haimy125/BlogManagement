package com.myph.blogmanagement.service;

import com.myph.blogmanagement.payload.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUser(String id);
}
