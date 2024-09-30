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
            responseData.setSuccess(false);
            responseData.setData("Thiếu thông tin đăng nhập.");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

        if (loginService.checkLogin(username, password)) {
            responseData.setData(jwtUtilsHelper.generateToken(username));
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();
        responseData.setData(loginService.signup(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
