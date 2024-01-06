package com.cherniavskyi.shop.security;

import com.cherniavskyi.shop.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


public class UserDetailsImpl extends User implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    public UserDetailsImpl(User user) {
        super(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getAddress(),
                user.getPhone(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRoles(),
                user.getPhoto(),
                user.getOrders(),
                user.getEmployeeDetail(),
                user.getCustomerDetail()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return super.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
