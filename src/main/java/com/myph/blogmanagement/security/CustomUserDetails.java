package com.myph.blogmanagement.security;

import com.myph.blogmanagement.model.Accounts;
import com.myph.blogmanagement.model.Permissions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Accounts accounts;
    private final List<Permissions> permissions;

    public CustomUserDetails(Accounts accounts, List<Permissions> permissions) {
        this.accounts = accounts;
        this.permissions = permissions;
    }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Permissions permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
        }

        return authorities;
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return accounts.getPassword();
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return accounts.getUsername();
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
