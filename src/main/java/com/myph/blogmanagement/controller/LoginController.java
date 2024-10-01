package com.myph.blogmanagement.controller;

import com.myph.blogmanagement.payload.ResponseData;
import com.myph.blogmanagement.payload.request.SignUpRequest;
import com.myph.blogmanagement.service.LoginService;
import com.myph.blogmanagement.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
        ResponseData responseData = new ResponseData();
        if (username == null || password == null) {
            responseData.setStatus(400);
            responseData.setSuccess(false);
            responseData.setData("");
            responseData.setDesc("Thiếu thông tin đăng nhập.");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

        if (loginService.checkLogin(username, password)) {
            responseData.setDesc("Đăng nhập thành công.");
            responseData.setData(jwtUtilsHelper.generateToken(username));
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setStatus(401);
            responseData.setData("");
            responseData.setSuccess(false);
            responseData.setDesc("Tên người dùng hoặc mật khẩu không chính xác.");
            return new ResponseEntity<>(responseData, HttpStatus.UNAUTHORIZED);// HTTP 401 Unauthorized
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();
        try {
            boolean success =  loginService.signup(signUpRequest);
            if (success) {
                responseData.setDesc("Signup successful.");
                return new ResponseEntity<>(responseData, HttpStatus.CREATED); // HTTP 201 Created
            }else {
                responseData.setStatus(400);
                responseData.setSuccess(false);
                responseData.setDesc("Signup failed.");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST); // HTTP 400 Bad Request
            }
        } catch (RuntimeException e) {
            responseData.setStatus(500);
            responseData.setSuccess(false);
            responseData.setDesc(e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 Internal Server Error
        }
    }
}
