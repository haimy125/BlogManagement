package com.myph.blogmanagement.service.Impl;

import com.myph.blogmanagement.model.Accounts;
import com.myph.blogmanagement.repository.AccountsRepository;
import com.myph.blogmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean checkLogin(String username, String password) {
        Accounts accounts = accountsRepository.findByUsername(username);
        return passwordEncoder.matches(password, accounts.getPassword());
    }
}
