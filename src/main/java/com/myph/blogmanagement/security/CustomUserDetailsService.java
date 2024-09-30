package com.myph.blogmanagement.security;

import com.myph.blogmanagement.model.*;
import com.myph.blogmanagement.repository.AccountsRepository;
import com.myph.blogmanagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountsRepository.findByUsername(username);
        if (accounts == null) {
            throw new UsernameNotFoundException(username);
        }
        // Tìm User dựa trên account_id
        Users user = usersRepository.findByAccounts_AccountId(accounts.getAccountId());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with account ID: " + accounts.getAccountId());
        }
        // Lấy role_id của người dùng
        Set<UserRoles> userRoles = user.getUserRoles();

        // Tạo danh sách quyền từ các roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoles userRole : userRoles) {

            Roles role = userRole.getRoles();

            // Thêm role như là một GrantedAuthority
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

            // Lấy danh sách quyền của role
            Set<RolePermissions> rolePermissionsSet = role.getRolePermissions();
            for (RolePermissions rolePermission : rolePermissionsSet) {
                authorities.add(new SimpleGrantedAuthority(rolePermission.getPermissions().getPermissionName()));
            }
        }
        // Trả về đối tượng UserDetails với danh sách quyền
        return new org.springframework.security.core.userdetails.User(accounts.getUsername(), accounts.getPassword(), authorities);
    }
}
