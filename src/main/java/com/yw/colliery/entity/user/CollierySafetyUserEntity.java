package com.yw.colliery.entity.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class CollierySafetyUserEntity implements UserDetails {

    private Integer id;

    private Integer roleId;

    private String roleName;

    private Integer departId;

    private String departName;

    private String userPwd;

    private String userName;

    private Date createDate;

    private String createUser;

    private Date modifyDate;

    private String modifyUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return userName;
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