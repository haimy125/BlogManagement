package com.myph.blogmanagement.service.Impl;

import com.myph.blogmanagement.model.Accounts;
import com.myph.blogmanagement.model.Roles;
import com.myph.blogmanagement.model.UserRoles;
import com.myph.blogmanagement.model.Users;
import com.myph.blogmanagement.model.keys.UserRolesKey;
import com.myph.blogmanagement.payload.request.SignInRequestDTO;
import com.myph.blogmanagement.payload.request.SignUpRequestDTO;
import com.myph.blogmanagement.repository.AccountsRepository;
import com.myph.blogmanagement.repository.RolesRepository;
import com.myph.blogmanagement.repository.UserRolesRepository;
import com.myph.blogmanagement.repository.UsersRepository;
import com.myph.blogmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean checkLogin(SignInRequestDTO signInRequestDTO) {
        Accounts accounts = accountsRepository.findByUsername(signInRequestDTO.getUsername());

        if (accounts==null) {
            throw new RuntimeException("Username không tồn tại!");
        }

        // Kiểm tra mật khẩu đã mã hóa có khớp với mật khẩu đầu vào không
        if (!passwordEncoder.matches(signInRequestDTO.getPassword(), accounts.getPassword())) {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }

        return passwordEncoder.matches(signInRequestDTO.getPassword(), accounts.getPassword());
    }

    /**
     * @param signUpRequest
     * @return
     */
    @Override
    public boolean signup(SignUpRequestDTO signUpRequest) {
        Users users = new Users();
        Accounts accounts = new Accounts();

        accounts.setAccountId(UUID.randomUUID().toString());
        accounts.setUsername(signUpRequest.getUsername());
        accounts.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        accounts.setDeletedFlag(false);

        Optional<Accounts> exist = Optional.ofNullable(accountsRepository.findByUsername(signUpRequest.getUsername()));
        if (exist.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        users.setUserId(Base64.getEncoder().encodeToString((signUpRequest.getUsername()+ System.currentTimeMillis()).getBytes()));
        users.setFirstName(signUpRequest.getFirst_name());
        users.setLastName(signUpRequest.getLast_name());
        users.setDeletedFlag(false);
        users.setAccounts(accounts);

        try {
            usersRepository.save(users);

            // Truy vấn role mặc định từ database
            Optional<Roles> rolesOptional = rolesRepository.findById("role2");
            if (!rolesOptional.isPresent()) {
                throw new RuntimeException("Role role2 không tồn tại trong cơ sở dữ liệu");
            } else {
                Roles roles = rolesOptional.get();

                // Tạo mối quan hệ giữa người dùng và vai trò
                UserRoles userRoles = new UserRoles();
                UserRolesKey key = new UserRolesKey(users.getUserId(), roles.getRoleId());

                userRoles.setUserRolesKey(key);
                userRoles.setUsers(users);
                userRoles.setRoles(roles);

                userRolesRepository.save(userRoles);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error during signup");
            return false;
        }
    }
}
