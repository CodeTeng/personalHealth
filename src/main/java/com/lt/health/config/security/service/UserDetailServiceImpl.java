package com.lt.health.config.security.service;

import com.lt.health.entity.Menu;
import com.lt.health.entity.User;
import com.lt.health.config.security.entity.LoginUser;
import com.lt.health.mapper.MenuMapper;
import com.lt.health.mapper.PermissionMapper;
import com.lt.health.mapper.RoleMapper;
import com.lt.health.mapper.UserMapper;
import com.lt.health.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 实现UserDetailsService接口 实现自定义登录逻辑
 * @author: 狂小腾
 * @date: 2022/3/26 21:01
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisUtils redisUtil;

    private final String USER_KEY_PRE = "userInfo";

    private final long expireTime = 5L;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        // 1.判断缓存中是否存在该用户 存在直接获取 不存在从数据库中获取并存入缓存中
        if (redisUtil.haskey(USER_KEY_PRE + username)) {
            // 缓存中存在 直接获取
            user = (User) redisUtil.getValue(USER_KEY_PRE + username);
            // 设置设置过期时间 5min
            redisUtil.expire(USER_KEY_PRE + username, expireTime);
        } else {
            // 从数据库中根据用户名获取用户
            user = userMapper.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户名或密码错误！！！");
            }
            if (user.getIsAdmin() == 1) {
                // 管理员拥有所有权限 不需要设置任何查询信息
                // 非管理员需要查询角色信息
                user.setRoles(roleMapper.findRolesByUserId(null));
                user.setPermissions(permissionMapper.findPermissionsByUserId(null));
                // 获取父级菜单
                List<Menu> menus = menuMapper.findMenusByUserId(null);
                // 获取子级菜单
                menus.forEach(item -> item.setChildren(menuMapper.findChildrenMenuByPidAndUserId(item.getId(), null)));
                user.setMenus(menus);
            } else {
                // 不是管理员需要设置菜单与角色条件
                user.setRoles(roleMapper.findRolesByUserId(user.getId()));
                user.setPermissions(permissionMapper.findPermissionsByUserId(user.getId()));
                // 获取父级菜单
                List<Menu> menus = menuMapper.findMenusByUserId(user.getId());
                // 获取子级菜单
                menus.forEach(item -> item.setChildren(menuMapper.findChildrenMenuByPidAndUserId(item.getId(), user.getId())));
                user.setMenus(menus);
            }
            // 向redis中存入该用户并指定过期时间 5min
            redisUtil.setValueTime(USER_KEY_PRE + username, user, expireTime);
        }
        return new LoginUser(user);
    }
}
