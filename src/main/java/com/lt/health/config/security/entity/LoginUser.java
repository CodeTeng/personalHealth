package com.lt.health.config.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lt.health.entity.Permission;
import com.lt.health.entity.Role;
import com.lt.health.entity.User;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description: security中的用户实体
 * @author: 狂小腾
 * @date: 2022/3/22 20:04
 */
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -7240861075043737094L;

    private User user;

    public LoginUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 权限数据
     *
     * @return 权限数据
     * @JsonIgnore 在返回的数据中，将该方法对应的属性数据给排除
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        List<Permission> permissions = user.getPermissions();
        if (roles != null && roles.size() > 0) {
            roles.forEach(role -> {
                if (StringUtils.isNotEmpty(role.getCode())) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
                }
            });
        }
        if (permissions != null && permissions.size() > 0) {
            permissions.forEach(permission -> {
                if (StringUtils.isNotEmpty(permission.getCode())) {
                    authorities.add(new SimpleGrantedAuthority(permission.getCode()));
                }
            });
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.getUserStatus() != 0;
    }
}
