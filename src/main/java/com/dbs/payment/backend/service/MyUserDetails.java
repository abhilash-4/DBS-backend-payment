package com.dbs.payment.backend.service;

import com.dbs.payment.backend.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private final Long id;

    private final String username;

    private final String password;


    private final String phone;

    private final Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(Long id, String username, String password,String  phone,Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.authorities = authorities;
    }

    public static  MyUserDetails build(User user){

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename().name()))
                .collect(Collectors.toList());


        return new MyUserDetails(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public Long getId() {
        return id;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
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
