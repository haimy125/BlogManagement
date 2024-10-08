package com.myph.blogmanagement.controller;

import com.myph.blogmanagement.model.Accounts;
import com.myph.blogmanagement.model.Users;
import com.myph.blogmanagement.payload.ResponseData;
import com.myph.blogmanagement.payload.request.SignInRequestDTO;
import com.myph.blogmanagement.payload.request.SignUpRequestDTO;
import com.myph.blogmanagement.repository.AccountsRepository;
import com.myph.blogmanagement.repository.UsersRepository;
import com.myph.blogmanagement.service.LoginService;
import com.myph.blogmanagement.utils.JwtUtilsHelper;
import jakarta.persistence.Access;
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

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequestDTO signInRequestDTO) {
        ResponseData responseData = new ResponseData();
        if (signInRequestDTO.getUsername() == null || signInRequestDTO.getPassword() == null) {
            responseData.setStatus(400);
            responseData.setSuccess(false);
            responseData.setData("");
            responseData.setDesc("Thiếu thông tin đăng nhập.");
            responseData.setUserId("Không tìm thấy user");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

        if (loginService.checkLogin(signInRequestDTO)) {
            Accounts accounts = accountsRepository.findByUsername(signInRequestDTO.getUsername());
            Users users = usersRepository.findByAccounts_AccountId(accounts.getAccountId());
            responseData.setDesc("Đăng nhập thành công.");
            responseData.setData(jwtUtilsHelper.generateToken(signInRequestDTO.getUsername()));
            responseData.setUserId(users.getUserId());
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setStatus(401);
            responseData.setData("");
            responseData.setSuccess(false);
            responseData.setUserId("Không tìm thấy user");
            responseData.setDesc("Tên người dùng hoặc mật khẩu không chính xác.");
            return new ResponseEntity<>(responseData, HttpStatus.UNAUTHORIZED);// HTTP 401 Unauthorized
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        ResponseData responseData = new ResponseData();
        try {
            boolean success =  loginService.signup(signUpRequestDTO);
            if (success) {
                responseData.setDesc("User registered successfully");
                responseData.setData("User registered successfully");
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
