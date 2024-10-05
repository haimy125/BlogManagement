package com.myph.blogmanagement.service;

import com.myph.blogmanagement.payload.request.SignInRequestDTO;
import com.myph.blogmanagement.payload.request.SignUpRequestDTO;

public interface LoginService {
    boolean checkLogin(SignInRequestDTO signInRequestDTO);
    boolean signup(SignUpRequestDTO signUpRequestDTO);
}
