package com.lt.health.config.security.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lt.health.entity.Permission;
import com.lt.health.entity.Role;
import com.lt.health.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @description: security中的用户实体
 * @author: 狂小腾
 * @date: 2022/3/22 20:04
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -7240861075043737094L;

    private User user;

    /**
     * 存储SpringSecurity所需要的权限信息的集合 该字段不序列化
     */
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginUser(User user) {
        this.user = user;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
        return user.getUserPassword();
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
