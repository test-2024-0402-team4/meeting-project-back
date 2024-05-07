package com.meeteam4.meeting.security;


import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class PrincipalUser implements UserDetails {

    private int userId;
    private String username;
    private String name;
    private int roleId;
    private String email;
    private int emailAuth;
    private int isAccountNonExpired;
    private int isAccountNonLocked;
    private int isCredentialsNonExpired;
    private int isEnabled;
    private List<SimpleGrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired == 1;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled == 1;
    }
}
