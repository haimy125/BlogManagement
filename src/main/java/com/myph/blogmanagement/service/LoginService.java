package com.myph.blogmanagement.service;

import com.myph.blogmanagement.payload.request.SignUpRequest;

public interface LoginService {
    boolean checkLogin(String username, String password);
    boolean signup(SignUpRequest signUpRequest);
}
